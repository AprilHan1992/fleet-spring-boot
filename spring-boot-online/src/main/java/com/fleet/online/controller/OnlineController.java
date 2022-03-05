package com.fleet.online.controller;

import com.fleet.online.listener.OnlineListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlineController {

    @RequestMapping("/online")
    public String online() {
        return "在线用户数量：" + OnlineListener.online;
    }
}
