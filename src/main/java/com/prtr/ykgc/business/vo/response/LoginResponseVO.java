package com.prtr.ykgc.business.vo.response;

import com.prtr.ykgc.business.pojo.UserPojo;
import com.prtr.ykgc.entity.RoleMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/11/25 11:42 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
public class LoginResponseVO {
    private UserPojo user;
    private List<RoleMenu> menus;
    public LoginResponseVO(){

    }
    public LoginResponseVO(UserPojo user, List<RoleMenu> menus) {
        this.user = user;
        this.menus = menus;
        makeSureMenusNotNull();
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public List<RoleMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<RoleMenu> menus) {
        this.menus = menus;
        makeSureMenusNotNull();
    }

    private void makeSureMenusNotNull() {
        if (ObjectUtils.isEmpty(menus)) {
            menus = new ArrayList<>();
        }
    }
}
