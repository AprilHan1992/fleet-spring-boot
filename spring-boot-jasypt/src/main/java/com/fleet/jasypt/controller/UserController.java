package com.fleet.jasypt.controller;

import com.fleet.jasypt.entity.User;
import com.fleet.jasypt.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author April Han
 */
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
    public void delete(Long id) {
        userService.delete(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping("/count")
    public int count() {
        return userService.count();
    }
}
