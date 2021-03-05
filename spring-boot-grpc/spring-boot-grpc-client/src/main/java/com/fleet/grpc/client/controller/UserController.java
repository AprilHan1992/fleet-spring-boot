package com.fleet.grpc.client.controller;

import com.fleet.grpc.client.service.UserService;
import com.fleet.grpc.common.entity.User;
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
    public User get(Integer id) {
        return userService.get(id);
    }
}
