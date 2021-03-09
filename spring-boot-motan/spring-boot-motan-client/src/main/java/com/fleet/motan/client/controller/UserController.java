package com.fleet.motan.client.controller;

import com.fleet.motan.common.entity.User;
import com.fleet.motan.common.service.UserService;
import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @MotanReferer(basicReferer = "clientBasicConfig")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }
}
