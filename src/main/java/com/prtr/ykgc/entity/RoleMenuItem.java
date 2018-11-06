package com.prtr.ykgc.entity;

public class RoleMenuItem {
    private Integer menuInnerId;

    private String roleId;

    private Integer roleMenuId;

    public Integer getMenuInnerId() {
        return menuInnerId;
    }

    public void setMenuInnerId(Integer menuInnerId) {
        this.menuInnerId = menuInnerId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }
}