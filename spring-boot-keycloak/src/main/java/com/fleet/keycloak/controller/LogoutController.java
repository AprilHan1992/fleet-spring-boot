package com.fleet.keycloak.controller;

import com.fleet.keycloak.json.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author April Han
 */
@RestController
public class LogoutController {

    /**
     * 登出
     */
    @RequestMapping(value = "/logout")
    public R logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return R.ok("登出成功");
    }
}
