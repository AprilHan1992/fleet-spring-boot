package com.fleet.filter.controller;

import com.fleet.filter.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/insert")
    private boolean insert(@RequestBody User user) {
        return true;
    }

    @RequestMapping(value = "/delete")
    private boolean delete(@RequestParam Long id) {
        return true;
    }

    @RequestMapping(value = "/update")
    private boolean update(@RequestBody User user) {
        return true;
    }

    @RequestMapping(value = "/get")
    private User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
