package com.fleet.shiro.online.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequiresPermissions("USER:GET")
    @RequestMapping("/get")
    public String get() {
        return "欢迎进入，user用户！";
    }
}
