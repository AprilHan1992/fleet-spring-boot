package com.fleet.activiemq.controller;

import com.fleet.activiemq.service.Sender;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;
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
        Destination destination = new ActiveMQTopic("test.queue");
        sender.send(destination, msg);
    }
}
