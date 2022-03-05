package com.fleet.shiro.config.filter;

import com.alibaba.fastjson.JSON;
import com.fleet.shiro.json.R;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author April Han
 */
public class FormAuthentication extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(R.error("未登录")));
        printWriter.flush();
        printWriter.close();
        return false;
    }
}
