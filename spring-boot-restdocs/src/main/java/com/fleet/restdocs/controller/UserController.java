package com.fleet.restdocs.controller;

import com.fleet.restdocs.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @CrossOrigin
    @RequestMapping(value = "/insert")
    private boolean insert(@RequestBody User user) {
        return true;
    }

    @RequestMapping(value = "/delete")
    private boolean delete(@RequestParam Long id) {
        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/update")
    private boolean update(@RequestBody User user) {
        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/get")
    private User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}
