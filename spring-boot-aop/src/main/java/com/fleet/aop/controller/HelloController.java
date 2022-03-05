package com.fleet.aop.controller;

import com.fleet.aop.aspect.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello！！！";
    }

    @Log
    @RequestMapping("/hello1")
    public String hello1() {
        return "hello！！！";
    }
}
