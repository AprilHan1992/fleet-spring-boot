package com.fleet.shiro.online.controller;

import com.fleet.shiro.online.util.ShiroUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    /**
     * 登出
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        ShiroUtil.logout();
        return "登出成功";
    }
}
