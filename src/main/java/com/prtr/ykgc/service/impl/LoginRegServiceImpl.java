package com.prtr.ykgc.service.impl;

import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.pojo.Token;
import com.prtr.ykgc.business.pojo.UserPojo;
import com.prtr.ykgc.business.response.BaseResult;
import com.prtr.ykgc.business.response.BaseResultFactory;
import com.prtr.ykgc.business.vo.response.LoginResponseVO;
import com.prtr.ykgc.component.JwtComponent;
import com.prtr.ykgc.entity.*;
import com.prtr.ykgc.mapper.RoleMenuItemMapper;
import com.prtr.ykgc.mapper.RoleMenuMapper;
import com.prtr.ykgc.mapper.UserMapper;
import com.prtr.ykgc.mapper.UserSessionMapper;
import com.prtr.ykgc.service.LoginRegService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Knox
 * @Date: 2018/11/25 6:54 PM
 * @Description: You Know
 * @Version 1.0
 */
@Service
public class LoginRegServiceImpl implements LoginRegService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserSessionMapper userSessionMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleMenuItemMapper roleMenuItemMapper;
    @Resource
    private JwtComponent jwtComponent;
    @Value("${project.secret.tokenName}")
    private String TOKEN_NAME;

    @Override
    @Transactional
    public BaseResult login(@NotNull String username,
                            @NotNull String password,
                            @NotNull Integer userType,
                            @NotNull String ip,
                            @NotNull Integer logFrom,
                            String version
    ) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //根据用户名找用户
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (!ObjectUtils.isEmpty(users)) {
            User user = users.get(0);
            if (password.equals(user.getUserPassword())) {
                //登录的UserSession
                UserSession session = new UserSession();
                session.setLastLoginTime(new Date());
                session.setLogFrom(logFrom);
                session.setLoginIp(ip);
                session.setSessionId(request.getSession().getId());
                session.setUsername(username);
                session.setUserType(userType);
                session.setVersion(version);
               // session.setSessionId(UUID.randomUUID().toString());
                userSessionMapper.insert(session);
                //response加入Token
                Token token = new Token();
                token.setLastRequestTime(System.currentTimeMillis());
                token.setUser(user);
                String encrypt = jwtComponent.encrypt(token);
                assert response != null;
                response.addCookie(new Cookie(TOKEN_NAME, encrypt));
                //获取该用户的所有菜单列表
                String roleId = user.getRoleId();
                RoleMenuItemExample roleMenuItemExample = new RoleMenuItemExample();
                roleMenuItemExample.createCriteria().andRoleIdEqualTo(roleId);
                List<RoleMenuItem> roleMenuItems = roleMenuItemMapper.selectByExample(roleMenuItemExample);
                List<RoleMenu> roleMenus = null;
                if (!ObjectUtils.isEmpty(roleMenuItems)) {
                    List<Integer> menuIds = new ArrayList<>();
                    roleMenuItems.forEach(item -> {
                        menuIds.add(item.getRoleMenuId());
                    });
                    RoleMenuExample roleMenuExample = new RoleMenuExample();
                    roleMenuExample.createCriteria().andMenuIdIn(menuIds);
                    roleMenus = roleMenuMapper.selectByExample(roleMenuExample);
                }
                //返回数据
                return BaseResultFactory.produceResult(Code.SUCCESS, new LoginResponseVO(new UserPojo(user.getUserId(), username), roleMenus));
            }
        }
        //登录失败
        return BaseResultFactory.produceResult(Code.USERNAME_OR_PSW_ERROR);
    }

    @Override
    public BaseResult register() {
        return null;
    }
}
