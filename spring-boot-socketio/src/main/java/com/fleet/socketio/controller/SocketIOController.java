package com.fleet.socketio.controller;

import com.fleet.socketio.entity.Msg;
import com.fleet.socketio.service.SocketIOService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/socketio")
public class SocketIOController {

    @Resource
    private SocketIOService socketIOService;

    @GetMapping(path = "/msg")
    String msg() {
        return "msg";
    }

    @Scheduled(fixedRate = 1000)
    @ResponseBody
    @RequestMapping(value = "/pushToUser")
    public void pushToClent() {
        Msg msg = new Msg(System.currentTimeMillis(), "推送消息");
        System.out.println("推送消息");
        socketIOService.pushToUser("1", msg);
    }
}
