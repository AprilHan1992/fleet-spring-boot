package com.fleet.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/hello")
    public String get() {
        return "你好，USER 角色用户！";
    }

    @PreAuthorize("hasAuthority('USER:GET')")
    @GetMapping("/get/{id}")
    public String get(@PathVariable String id) {
        return "id : " + id;
    }
}
