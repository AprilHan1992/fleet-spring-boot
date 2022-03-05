package com.fleet.properties.controller;

import com.fleet.properties.property.UserProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserProperties userProperties;

    @RequestMapping("/get")
    public Object get() {
        return userProperties;
    }
}
