package com.prtr.ykgc.business.aop;

import com.prtr.ykgc.business.annotation.AuthChecker;
import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.constant.UserRole;
import com.prtr.ykgc.business.response.BaseResult;
import com.prtr.ykgc.business.response.BaseResultFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @Author: Knox
 * @Date: 2018/11/6 9:27 PM
 * @Description: You Know
 * @Version 1.0
 */
@Configuration
@Aspect
@Component
public class AuthorityAop {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityAop.class);


    /**
     * 带有自定义注解的方法会被AOP拦截
     */
    @Pointcut("@annotation(com.prtr.ykgc.business.annotation.AuthChecker)")
    public void auth() {
    }

    /**
     * 查看用户的登录Token是否已经过期，并且查看用户是否有权调用API
     *
     * @param point 切入点
     * @return 函数的返回值
     */
    @Around("auth()")
    public BaseResult invoke(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                flag = true;
                if (!"caohui".equals(cookie.getValue())) {
                    //return BaseResultFactory.produceResult(1, "你不是曹辉！用户权限拒绝！", Code.AUTH_DENIED);
                }
                break;
            }
        }
        //TODO 正常情况下这里需要编码后再添加到Cookie，以后会做
        if (!flag) {
            response.addCookie(new Cookie("username", "caohui"));
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        AuthChecker authChecker = method.getAnnotation(AuthChecker.class);

        UserRole[] roles = authChecker.value();
        logger.error("允许的用户权限：" + Arrays.asList(roles));
        //TODO 根据roles检查用户权限

        return (BaseResult) point.proceed(args);
    }


}
