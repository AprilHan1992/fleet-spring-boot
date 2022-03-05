package com.fleet.shiro.config.handler;

import com.fleet.shiro.json.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author April Han
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public R handleAuthorizationException() {
        return R.error("未授权");
    }
}
