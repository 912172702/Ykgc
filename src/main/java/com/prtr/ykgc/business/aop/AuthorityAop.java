package com.prtr.ykgc.business.aop;

import com.prtr.ykgc.business.annotation.AuthChecker;
import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.constant.UserRole;
import com.prtr.ykgc.business.pojo.Token;
import com.prtr.ykgc.business.response.BaseResult;
import com.prtr.ykgc.business.response.BaseResultFactory;
import com.prtr.ykgc.component.JwtComponent;
import com.prtr.ykgc.entity.User;
import com.prtr.ykgc.entity.UserSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;


/**
 * @Author: Knox
 * @Date: 2018/11/6 9:27 PM
 * @Description: You Know
 * @Version 1.0
 */
@Configuration
@Aspect
public class AuthorityAop {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityAop.class);
    @Value("${project.secret.tokenName}")
    private String TOKEN_NAME;
    @Resource
    private JwtComponent jwtComponent;

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
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (TOKEN_NAME.equals(cookie.getName())) {
                    //flag = true;
                    String tokenJsonString = cookie.getValue();
                    Token token = jwtComponent.decrypt(tokenJsonString);
                    User user = token.getUser();
                    UserRole role = UserRole.parseFromRoleId(user.getRoleId());
                    if (!ObjectUtils.isEmpty(role)) {
                        //获得API规定的角色列表
                        Method method = ((MethodSignature) point.getSignature()).getMethod();
                        AuthChecker authChecker = method.getAnnotation(AuthChecker.class);
                        UserRole[] roles = authChecker.value();
                        logger.info("允许的用户权限：" + Arrays.asList(roles));
                        logger.info("该用户权限 : " + role);
                        //根据roles检查用户权限
                        for (UserRole auth : roles) {
                            if (role == auth) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        if (!flag) {
            logger.warn("用户权限拒绝！");
            return BaseResultFactory.produceResult(Code.PERMISSION_DENIED);
        }
        logger.info("允许访问");
        return (BaseResult) point.proceed(args);
    }


}
