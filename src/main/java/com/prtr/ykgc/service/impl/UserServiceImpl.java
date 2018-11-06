package com.prtr.ykgc.service.impl;

import com.prtr.ykgc.entity.User;
import com.prtr.ykgc.mapper.UserMapper;
import com.prtr.ykgc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: Knox
 * @Date: 2018/11/6 4:06 PM
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
