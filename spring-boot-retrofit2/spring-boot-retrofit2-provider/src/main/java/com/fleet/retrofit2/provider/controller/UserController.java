package com.fleet.retrofit2.provider.controller;

import com.fleet.retrofit2.provider.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public Boolean insert(@RequestBody User user) {
        return true;
    }

    @RequestMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return true;
    }

    @RequestMapping("/update")
    public Boolean update(@RequestBody User user) {
        return true;
    }

    @RequestMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setName("fleet");
        return user;
    }

    @RequestMapping("/list")
    public List<User> list(Map<String, Object> map) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        userList.add(user);
        return userList;
    }
}
