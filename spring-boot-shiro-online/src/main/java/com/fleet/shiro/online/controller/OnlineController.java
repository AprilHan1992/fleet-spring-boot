package com.fleet.shiro.online.controller;

import com.fleet.shiro.online.config.listener.OnlineListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OnlineController {

    @Resource
    OnlineListener sessionListener;

    @RequestMapping("/online")
    public String online() {
        return "在线用户数量：" + sessionListener.get();
    }
}
