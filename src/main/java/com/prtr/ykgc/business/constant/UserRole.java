package com.prtr.ykgc.business.constant;

import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/11/6 9:51 PM
 * @Description: You Know
 * @Version 1.0
 */

public enum UserRole {
    MANAGER(1, "管理员"),
    SELLER(2, "经销商");

    private int roleId;
    private String roleName;

    UserRole(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
