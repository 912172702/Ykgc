package com.prtr.ykgc.business.exception;

import com.prtr.ykgc.business.constant.Code;
import com.prtr.ykgc.business.response.BaseResult;

/**
 * @Author: Knox
 * @Date: 2018/11/6 8:16 PM
 * @Description: 业务异常
 * @Version 1.0
 */
public class BusinessException extends BaseException {
    public BusinessException() {
    }

    public BusinessException(Code code) {
        super(code.getCode(), code.getMsg());
    }

    public BusinessException(int codeInt, String errorMsg) {
        super(codeInt, errorMsg);
    }
}
