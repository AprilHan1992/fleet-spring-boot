package com.fleet.cors.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 跨域配置（一）：注解方式
 * 在需要跨域的 Controller 层的类或方法上加上注解 @CrossOrigin
 *
 * @author April Han
 */
// @CrossOrigin(origins = {"http://localhost:8000"})
@RestController
public class HelloController {

    // @CrossOrigin(origins = {"http://localhost:8000"})
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
