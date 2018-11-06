package com.prtr.ykgc.business.exception;

import com.prtr.ykgc.business.response.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Knox
 * @Date: 2018/11/6 8:18 PM
 * @Description: 异常统一在这里捕获
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobeExceptionHandlerAdvice {
    // 打印日志
    private static final Logger logger = LoggerFactory.getLogger(GlobeExceptionHandlerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public BaseResult handBusinessException(BusinessException be) {
        logger.info("异常来了！");
        be.printStackTrace();
        return be.getBaseResult();
    }

}
