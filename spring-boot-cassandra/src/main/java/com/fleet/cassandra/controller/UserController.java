package com.fleet.cassandra.controller;

import com.fleet.cassandra.entity.User;
import com.fleet.cassandra.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping("/list")
    public Iterable<User> list() {
        return userService.select();
    }
}
