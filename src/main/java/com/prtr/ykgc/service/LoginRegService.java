package com.prtr.ykgc.service;

import com.prtr.ykgc.business.response.BaseResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @Author: Knox
 * @Date: 2018/11/25 6:48 PM
 * @Description: You Know
 * @Version 1.0
 */
public interface LoginRegService {
    BaseResult login(@NotNull String username,
                     @NotNull String password,
                     @NotNull Integer userType,
                     @NotNull String ip,
                     @NotNull Integer logFrom,
                     String version);

    BaseResult register();
}
