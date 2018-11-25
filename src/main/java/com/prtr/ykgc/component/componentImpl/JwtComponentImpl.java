package com.prtr.ykgc.component.componentImpl;

import com.google.gson.Gson;
import com.prtr.ykgc.business.pojo.Token;
import com.prtr.ykgc.component.JwtComponent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @Author: Knox
 * @Date: 2018/11/25 7:35 PM
 * @Description: You Know
 * @Version 1.0
 */
@Component
public class JwtComponentImpl implements JwtComponent {

    @Value("project.secret.secretKey")
    private static String secretKey;
    private Gson gson = new Gson();

    /**
     * @param token 用户会话信息和超时时间
     * @return 加密后的字符串
     */
    @Override
    public String encrypt(@NotNull Token token) {
        //加密方式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setSubject(gson.toJson(token))
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .signWith(signatureAlgorithm, secretKey);

        return builder.compact();
    }

    /**
     * @param jsonString 加密后的字符串
     * @return 解密后的Token
     */
    @Override
    public Token decrypt(@NotNull String jsonString) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jsonString).getBody();
        String tokenJsonString = claims.getSubject();
        if (ObjectUtils.isEmpty(tokenJsonString)) return null;
        return gson.fromJson(tokenJsonString, Token.class);
    }
}
