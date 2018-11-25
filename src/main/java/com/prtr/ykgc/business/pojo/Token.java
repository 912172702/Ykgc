package com.prtr.ykgc.business.pojo;

import com.prtr.ykgc.entity.UserSession;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Knox
 * @Date: 2018/11/25 5:22 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
public class Token implements Serializable {
    private long lastRequestTime;
    private UserSession session;
}
