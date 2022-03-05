package com.fleet.keycloak.controller;

import com.fleet.keycloak.json.R;
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

    @RequestMapping("/hello")
    public R get() {
        return R.ok("你好，USER 角色用户！");
    }

    @GetMapping("/get/{id}")
    public R get(@PathVariable String id) {
        return R.ok("id : " + id);
    }
}

