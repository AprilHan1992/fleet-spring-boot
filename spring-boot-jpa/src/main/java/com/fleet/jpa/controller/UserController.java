package com.fleet.jpa.controller;

import com.fleet.jpa.entity.User;
import com.fleet.jpa.repository.UserRepository;
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
