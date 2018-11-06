package com.prtr.ykgc.business.response;

import com.prtr.ykgc.business.constant.Code;

/**
 * @Author: Knox
 * @Date: 2018/11/6 8:04 PM
 * @Description: 统一返回JSON类型
 * @Version 1.0
 */
@SuppressWarnings("unchecked")
public class BaseResultFactory {
    public static BaseResult produceEmptyResult(Code code) {
        return new BaseResult(code);
    }

    public static BaseResult produceEmptyResult(int codeInt, String msg) {
        return new BaseResult(codeInt, msg);
    }

    public static BaseResult produceResult(int codeInt, String msg, Object data) {
        return new BaseResult(codeInt, msg, data);
    }

    public static BaseResult produceResult(Code code, Object data) {
        return new BaseResult(code.getCode(), code.getMsg(), data);
    }

}
