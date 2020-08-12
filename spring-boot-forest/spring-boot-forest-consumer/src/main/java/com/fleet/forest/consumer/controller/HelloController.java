package com.fleet.forest.consumer.controller;

import com.fleet.forest.consumer.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    @Resource
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return helloService.hello();
    }
}
