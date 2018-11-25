package com.prtr.ykgc.business.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Knox
 * @Date: 2018/11/25 5:55 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
public class LoginVO {
    @NotNull String username;
    @NotNull String password;
    @NotNull Integer userType;
    @NotNull String ip;
    @NotNull Integer logFrom;
    String version;
}
