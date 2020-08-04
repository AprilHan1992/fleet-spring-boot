package com.fleet.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    public String send() {
        String context = "简单消息发送";
        logger.info("简单消息发送时间：" + new Date());
        amqpTemplate.convertAndSend("messages", context);
        return "发送成功";
    }
}
