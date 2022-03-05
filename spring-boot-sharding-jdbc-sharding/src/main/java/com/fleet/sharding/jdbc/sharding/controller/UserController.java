package com.fleet.sharding.jdbc.sharding.controller;

import com.fleet.sharding.jdbc.sharding.entity.User;
import com.fleet.sharding.jdbc.sharding.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping("/list")
    public Iterable<User> list() {
        return userRepository.findAll();
    }
}
