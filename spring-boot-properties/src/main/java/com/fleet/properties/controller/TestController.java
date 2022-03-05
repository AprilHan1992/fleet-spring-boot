package com.fleet.properties.controller;

import com.fleet.properties.property.TestProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestProperties testProperties;

    @RequestMapping("/getUserName")
    public String getUserName() {
        return testProperties.getUserName();
    }

    @RequestMapping("/getUserAge")
    public Integer getUserAge() {
        return testProperties.getUserAge();
    }
}
