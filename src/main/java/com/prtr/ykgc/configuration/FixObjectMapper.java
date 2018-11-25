package com.prtr.ykgc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Knox
 * @Date: 2018/11/6 10:47 PM
 * @Description: You Know
 * @Version 1.0
 */
@Configuration
public class FixObjectMapper {
    @Bean
    public ObjectMapper objectMapper() {
        //当序列化空的对象时，不用报错
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

}
