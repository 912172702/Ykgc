package com.prtr.ykgc.business.constant;

/**
 * @Author: Knox
 * @Date: 2018/11/25 7:28 PM
 * @Description: You Know
 * @Version 1.0
 */
public enum RoleLevel {
    SYSTEM(0, "系统级"),
    CITY(1, "市级"),
    AREA(2, "区级"),
    POLICE(3, "派出所级别");
    private int code;
    private String message;

    RoleLevel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
