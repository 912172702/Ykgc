package com.prtr.ykgc;

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
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
        user.setAuthority("1");
        user.setCanDel(0);
        user.setCarId("1");
        user.setUserId(UUID.randomUUID().toString());
        //...
        userService.insertUser(user);
    }
}