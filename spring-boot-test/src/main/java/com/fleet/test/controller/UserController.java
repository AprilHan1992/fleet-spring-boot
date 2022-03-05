package com.fleet.test.controller;

import com.fleet.test.entity.User;
import com.fleet.test.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert")
    public boolean insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }
}
