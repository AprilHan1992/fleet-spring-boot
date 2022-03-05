package com.fleet.mybatis.generator.controller;

import com.fleet.mybatis.generator.entity.User;
import com.fleet.mybatis.generator.service.UserService;
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
        userService.insert(user);
    }

    @RequestMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping("/get")
    public User get(Integer id) {
        return userService.get(id);
    }
}
