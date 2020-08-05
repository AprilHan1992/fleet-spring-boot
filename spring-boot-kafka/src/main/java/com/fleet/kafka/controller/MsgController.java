package com.fleet.kafka.controller;

import com.fleet.kafka.service.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Resource
    private Sender sender;

    @RequestMapping("/send")
    public void send() {
        String msg = "fleet";
        logger.info("发送消息：" + msg);
        logger.info("发送消息时间：" + new Date());
        sender.send(msg);
    }
}
