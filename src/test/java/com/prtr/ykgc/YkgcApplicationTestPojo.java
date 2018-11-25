package com.prtr.ykgc;

import com.prtr.ykgc.business.interceptor.TokenInterceptor;
import com.prtr.ykgc.entity.User;
import com.prtr.ykgc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: Knox
 * @Date: 2018/11/6 3:35 PM
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class YkgcApplicationTestPojo {
    @Resource
    TokenInterceptor tokenInterceptor;

    @Test
    public void test() {
        System.out.println(tokenInterceptor.getTokenExpTime());
        System.out.println(tokenInterceptor.getPathPatterns());
        System.out.println(tokenInterceptor.getExcludePathPatterns());
    }
}