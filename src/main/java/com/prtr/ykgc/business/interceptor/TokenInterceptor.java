package com.prtr.ykgc.business.interceptor;

import com.google.gson.Gson;
import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.pojo.Token;
import com.prtr.ykgc.business.response.BaseResultFactory;
import com.prtr.ykgc.component.JwtComponent;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/11/25 5:41 PM
 * @Description: Token拦截器，拦截除了登录请求外的其他请求，检查是否登录和Token是过期
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "project.interceptor.token")
public class TokenInterceptor implements HandlerInterceptor {
    private long tokenExpTime;
    private List<String> pathPatterns = new ArrayList<>();
    private List<String> excludePathPatterns = new ArrayList<>();

    @Resource
    private JwtComponent jwtComponent;
    private Gson gson = new Gson();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Code flag = Code.NOT_CHECKED;
        Cookie[] cookies = request.getCookies();
        if (ObjectUtils.isEmpty(cookies))
            flag = Code.NOT_LOGIN;
        else {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String tokenJsonString = cookie.getValue();
                    Token token = jwtComponent.decrypt(tokenJsonString);
                    if (!ObjectUtils.isEmpty(token)) {
                        long lastRequest = token.getLastRequestTime();
                        if (System.currentTimeMillis() - lastRequest > tokenExpTime) {
                            flag = Code.TOKEN_EXP;
                        } else {
                            flag = Code.GOOD_TOKEN;
                        }
                    }
                }
            }
        }

        switch (flag) {
            case NOT_CHECKED:
            case NOT_LOGIN:
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(gson.toJson(BaseResultFactory.produceResult(Code.NOT_LOGIN)));
                return false;
            case TOKEN_EXP:
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(gson.toJson(BaseResultFactory.produceResult(Code.TOKEN_EXP)));
                return false;
            default:
                break;
        }
        return true;
    }
}


