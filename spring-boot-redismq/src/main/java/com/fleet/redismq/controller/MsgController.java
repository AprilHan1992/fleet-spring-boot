package com.fleet.redismq.controller;

import com.fleet.redismq.service.Sender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Resource
    private Sender sender;

    @RequestMapping("/send")
    public void send(String msg) {
        sender.send(msg);
    }
}
