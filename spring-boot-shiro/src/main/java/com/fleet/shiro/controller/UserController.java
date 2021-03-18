package com.fleet.shiro.controller;

import com.fleet.shiro.json.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequiresRoles("USER")
    @RequestMapping("/hello")
    public R get() {
        return R.ok("你好，USER 角色用户！");
    }

    @RequiresPermissions("USER:GET")
    @GetMapping("/get/{id}")
    public R get(@PathVariable String id) {
        return R.ok("id : " + id);
    }
}

