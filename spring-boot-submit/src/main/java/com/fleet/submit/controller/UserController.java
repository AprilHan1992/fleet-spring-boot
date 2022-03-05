package com.fleet.submit.controller;

import com.alibaba.fastjson.JSON;
import com.fleet.submit.aspect.annotation.NoRepeatSubmit;
import com.fleet.submit.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @NoRepeatSubmit
    @RequestMapping("/insert")
    public String insert(@RequestBody User user) {
        return JSON.toJSONString(user);
    }
}
