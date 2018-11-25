package com.prtr.ykgc.controller;

import com.alibaba.druid.VERSION;
import com.prtr.ykgc.business.annotation.AuthChecker;
import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.constant.UserRole;
import com.prtr.ykgc.business.exception.BusinessException;
import com.prtr.ykgc.business.response.BaseResult;
import com.prtr.ykgc.business.response.BaseResultFactory;
import com.prtr.ykgc.entity.User;
import com.prtr.ykgc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: Knox
 * @Date: 2018/11/6 4:10 PM
 * @Version 1.0
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/Ykgc/v1")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "/insert", notes = "新增用户", response = BaseResult.class)
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    @AuthChecker({UserRole.MANAGER})
    public BaseResult insertUser(@RequestBody User user) throws BusinessException {
        user.setUserId(UUID.randomUUID().toString());

        //测试GlobeExceptionHandlerAdvice是否起作用
        if ("1".equals(user.getCarId())) {
            throw new BusinessException(0, "测试异常捕获ControllerAdvice");
        }
        userService.insertUser(user);
        return BaseResultFactory.produceEmptyResult(Code.SUCCESS);
    }
}
