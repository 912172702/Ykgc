package com.prtr.ykgc.business.constant;


import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/11/6 7:53 PM
 * @Description: You Know
 * @Version 1.0
 */
public enum Code {
    UNDEFINED(-0x0001, "未定义"),
    SUCCESS(0x0000, "成功"),
    PERMISSION_DENIED(0x0001, "权限拒绝"),
    //登录
    USERNAME_OR_PSW_ERROR(0x0002, "用户名或密码错误"),
    LOGIN_INFO_NOT_COMPLETE(0x0007, "登录信息不完整"),
    //Token
    NOT_CHECKED(0x0003, "未检查"),
    GOOD_TOKEN(0x0004, "检查通过"),
    NOT_LOGIN(0x0005, "未登录"),
    TOKEN_EXP(0x0006, "Token过期，请重新登录");
    private int code;
    private String msg;

    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static String getMsgByCodeInt(int codeInt) {
        for (Code e : Code.values()) {
            if (e.getCode() == codeInt) {
                return e.msg;
            }
        }
        throw new IllegalArgumentException("未定义的code码:" + codeInt);
    }

    public static Code getCodeByCodeInt(int codeInt) {
        for (Code code : Code.values()) {
            if (code.getCode() == codeInt) {
                return code;
            }
        }
        throw new IllegalArgumentException("未定义的code码:" + codeInt);
    }

}
