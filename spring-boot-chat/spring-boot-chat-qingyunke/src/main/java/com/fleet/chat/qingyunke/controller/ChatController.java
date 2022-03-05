package com.fleet.chat.qingyunke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 连接青云客机器人聊天
 *
 * @author April Han
 */
@Controller
public class ChatController {

    @GetMapping("chat")
    public String hello1() {
        return "chat";
    }
}
