package com.fleet.websocket.controller;

import com.fleet.websocket.entity.Msg;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping(path = "/msg1")
    String msg1() {
        return "msg1";
    }

    @GetMapping(path = "/msg2")
    String msg2() {
        return "msg2";
    }

    /**
     * 广播推送消息
     */
    @Scheduled(fixedRate = 1000)
    public void sendTopicMessage() {
        Msg msg = new Msg(System.currentTimeMillis(), "广播推送消息");
        simpMessagingTemplate.convertAndSend("/topic/getResponse", msg);
    }

    /**
     * 一对一推送消息
     */
    @Scheduled(fixedRate = 1000)
    public void sendQueueMessage() {
        Msg msg = new Msg(System.currentTimeMillis(), "一对一推送消息");
        simpMessagingTemplate.convertAndSendToUser("1", "/queue/getResponse", msg);
    }
}
