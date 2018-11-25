package com.prtr.ykgc.component;

import com.prtr.ykgc.business.pojo.Token;
import com.prtr.ykgc.entity.UserSession;

/**
 * @Author: Knox
 * @Date: 2018/11/25 7:07 PM
 * @Description: You Know
 * @Version 1.0
 */
public interface JwtComponent {
    String encrypt(Token token);
    Token decrypt(String jsonString);
}
