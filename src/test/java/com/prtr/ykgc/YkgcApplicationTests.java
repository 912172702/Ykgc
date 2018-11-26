package com.prtr.ykgc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YkgcApplicationTests {
    @Resource
    ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
        System.out.println(objectMapper.getSerializationConfig().isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
    }

}
