package com.fleet.cors.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 跨域配置（三），在接口类上添加注解
 *
 * @author April Han
 */
// @CrossOrigin(origins = {"http://localhost:8000"})
@RestController
public class HelloController {

    /**
     * 跨域配置（四），在接口方法上添加注解
     */
    // @CrossOrigin(origins = {"http://localhost:8000"})
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
