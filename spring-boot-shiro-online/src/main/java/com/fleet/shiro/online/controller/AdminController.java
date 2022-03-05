package com.fleet.shiro.online.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiresRoles("ADMIN")
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/get")
    public String get() {
        return "欢迎进入，admin用户！";
    }
}
