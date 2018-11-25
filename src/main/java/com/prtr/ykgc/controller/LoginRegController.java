package com.prtr.ykgc.controller;

import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.response.BaseResult;
import com.prtr.ykgc.business.response.BaseResultFactory;
import com.prtr.ykgc.business.vo.request.LoginVO;
import com.prtr.ykgc.service.LoginRegService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Knox
 * @Date: 2018/11/25 5:52 PM
 * @Description: 登录和注册业务
 * @Version 1.0
 */
@Api(description = "登录和注册接口")
@RestController
@RequestMapping("/Ykgc/v1")
public class LoginRegController {

    @Resource
    private LoginRegService loginRegService;

    @ApiParam(name = "loginVO", value = "登录信息")
    @ApiOperation(value = "/login", notes = "用户登录", response = BaseResult.class)
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResult Login(@RequestBody LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();
        String ip = loginVO.getIp();
        Integer logFrom = loginVO.getLogFrom();
        String version = loginVO.getVersion();
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(userType) || ObjectUtils.isEmpty(ip) || ObjectUtils.isEmpty(logFrom)) {
            return BaseResultFactory.produceResult(Code.LOGIN_INFO_NOT_COMPLETE);
        }
        return loginRegService.login(username, password, userType, ip, logFrom, version);
    }
}
