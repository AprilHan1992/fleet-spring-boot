package com.fleet.oauth2.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping("/hello")
    public String hello() {
        return "你好，游客！";
    }
}
