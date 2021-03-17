package com.fleet.oauth2.resource.config.handler;

import com.alibaba.fastjson.JSON;
import com.fleet.oauth2.resource.json.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        Throwable cause = authenticationException.getCause();
        if (cause instanceof InvalidTokenException) {
            printWriter.write(JSON.toJSONString(R.error("无效的 token")));
        } else {
            printWriter.write(JSON.toJSONString(R.error()));
        }
        printWriter.flush();
        printWriter.close();


//        try {
//            if (cause instanceof InvalidTokenException) {
//                response.getWriter().write(ResultJsonUtil.build(
//                        ResponseCodeConstant.REQUEST_FAILED,
//                        ResponseStatusCodeConstant.OAUTH_TOKEN_FAILURE,
//                        ResponseMessageConstant.OAUTH_TOKEN_ILLEGAL
//                ));
//            } else {
//                response.getWriter().write(ResultJsonUtil.build(
//                        ResponseCodeConstant.REQUEST_FAILED,
//                        ResponseStatusCodeConstant.OAUTH_TOKEN_MISSING,
//                        ResponseMessageConstant.OAUTH_TOKEN_MISSING
//                ));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
