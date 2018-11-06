package com.prtr.ykgc.business.annotation;

import com.prtr.ykgc.business.constant.UserRole;

import java.lang.annotation.*;

/**
 * @Author: Knox
 * @Date: 2018/11/6 9:50 PM
 * @Description: You Know
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthChecker {
    UserRole[] value();
}
