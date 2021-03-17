package com.fleet.security.config.handler;

import com.alibaba.fastjson.JSON;
import com.fleet.security.json.R;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author April Han
 */
@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String msg;
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            msg = "账户名或者密码输入错误";
        } else if (exception instanceof LockedException) {
            msg = "账户被锁定";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "密码过期";
        } else if (exception instanceof AccountExpiredException) {
            msg = "账户过期";
        } else if (exception instanceof DisabledException) {
            msg = "账户被禁用";
        } else {
            msg = "登录失败";
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(R.error(msg)));
        printWriter.flush();
        printWriter.close();
    }
}
