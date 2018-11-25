package com.prtr.ykgc.business.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author: Knox
 * @Date: 2018/11/25 11:47 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private String userId;
    private String username;
}
