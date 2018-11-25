package com.prtr.ykgc.business.response;

import com.prtr.ykgc.business.constant.Code;

import java.io.Serializable;

/**
 * @Author: Knox
 * @Date: 2018/11/6 7:50 PM
 * @Description: 统一返回的JSON类型
 * @Version 1.0
 */
@SuppressWarnings("unchecked")
public final class BaseResult<T> {
    private int status;
    private String message;
    private T data = (T) new Object();

    public BaseResult() {
        this.data = (T) new Object();
    }

    public BaseResult(String msg) {
        this();
        this.status = 200;
        this.message = msg;
    }

    public BaseResult(int code, String message) {
        this.status = code;
        this.message = message;
    }

    public BaseResult(int code, String message, T data) {
        this.status = code;
        this.message = message;
        this.data = data;
    }

    // 与Code码交互
    public BaseResult(Code code) {
        this();
        this.status = code.getCode();
        this.message = code.getMsg();
    }

    /**
     * 返回结果代码code和消息msg，不需要返回值
     *
     * @param code 结果类型
     */
    public final void returnWithoutValue(Code code) {
        this.status = code.getCode();
        this.message = code.getMsg();
    }

    /**
     * 返回结果代码code和消息msg，并返回值
     *
     * @param code   结果类型
     * @param object 返回值
     */
    public final void returnWithValue(Code code, T object) {
        returnWithoutValue(code);
        this.data = object;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
