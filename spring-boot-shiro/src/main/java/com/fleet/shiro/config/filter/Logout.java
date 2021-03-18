package com.fleet.shiro.config.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author April Han
 */
public class Logout extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        return true;
    }
}
