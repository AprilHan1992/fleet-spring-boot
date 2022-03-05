package com.fleet.redismq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 继承 MessageListener
 *
 * @author April Han
 */
@Component
public class Receiver2 implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(Receiver2.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("Receiver2 接收消息：" + new String(message.getBody()));
        logger.info("Receiver2 接收消息时间：" + new Date());
    }
}
