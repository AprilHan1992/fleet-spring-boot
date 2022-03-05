package com.fleet.lombok.controller;

import com.fleet.lombok.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/get")
    public User get() {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
