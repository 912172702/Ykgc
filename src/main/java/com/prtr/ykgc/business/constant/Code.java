package com.prtr.ykgc.business.constant;


import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/11/6 7:53 PM
 * @Description: You Know
 * @Version 1.0
 */
public enum Code {
    UNDEFINED(-1, "未定义"),
    SUCCESS(0, "成功"),
    AUTH_DENIED(1, "权限拒绝");

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
