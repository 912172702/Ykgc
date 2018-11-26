package com.prtr.ykgc.business.constant;

/**
 * @Author: Knox
 * @Date: 2018/11/6 9:51 PM
 * @Description: You Know
 * @Version 1.0
 */

public enum UserRole {
    MANAGER("1", "管理员"),
    SELLER("2", "经销商");

    private String roleId;
    private String roleName;

    UserRole(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static UserRole parseFromRoleId(String roleId) {
        for (UserRole role : UserRole.values()) {
            if (roleId.equals(role.getRoleId())) {
                return role;
            }
        }
        return null;
    }
}
