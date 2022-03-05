package com.fleet.jsoup.controller;

import com.alibaba.fastjson.JSON;
import com.fleet.jsoup.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public String insert(@RequestBody User user) {
        return JSON.toJSONString(user);
    }
}
