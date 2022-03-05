package com.fleet.shiro.config.filter;

import com.alibaba.fastjson.JSON;
import com.fleet.shiro.json.R;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author April Han
 */
public class RolesAuthorization extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            printWriter.write(JSON.toJSONString(R.error("未登录")));
        } else {
            printWriter.write(JSON.toJSONString(R.error("未授权")));
        }
        printWriter.flush();
        printWriter.close();
        return false;
    }
}
