package com.fleet.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author April Han
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("messages"))
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String msg) {
        logger.info("接收消息：" + msg);
        logger.info("接收消息时间：" + new Date());
    }
}
