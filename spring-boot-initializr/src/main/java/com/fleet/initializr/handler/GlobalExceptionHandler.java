package com.fleet.initializr.handler;

import com.fleet.initializr.json.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author April Han
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public R handleBaseException(BaseException e) {
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 全局异常捕捉处理
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R handleException() {
        return R.error();
    }
}
