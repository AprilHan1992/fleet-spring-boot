package com.fleet.forest.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello forest";
    }
}
