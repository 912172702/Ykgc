package com.prtr.ykgc.business.exception;

import com.prtr.ykgc.business.response.BaseResult;
import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/11/6 8:10 PM
 * @Description: 基础异常类
 * @Version 1.0
 */
@Data
public abstract class BaseException extends Exception {
    private int codeInt;
    private String msg;
    private BaseResult baseResult;

    BaseException() {
        super();
    }

    BaseException(int codeInt, String msg) {
        super(msg);
        this.baseResult = new BaseResult(codeInt, msg);
    }

}
