package com.fleet.returnvalue.controller;

import com.fleet.returnvalue.entity.User;
import com.fleet.returnvalue.json.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public R insert(@RequestBody User user) {
        return R.ok();
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return new User(1L, "fleet");
    }

    @RequestMapping("/list")
    public List<User> list() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "fleet"));
        return list;
    }
}
