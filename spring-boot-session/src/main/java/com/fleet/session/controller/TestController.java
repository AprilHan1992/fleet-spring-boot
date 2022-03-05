package com.fleet.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @GetMapping("/set")
    public void set(HttpSession session) {
        session.setAttribute("user", "fleet");
    }

    @GetMapping("/get")
    public Object get(HttpSession session) {
        return session.getAttribute("user");
    }
}
