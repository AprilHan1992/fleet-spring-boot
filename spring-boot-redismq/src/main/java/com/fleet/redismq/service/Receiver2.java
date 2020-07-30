package com.fleet.redismq.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 继承 MessageListener
 *
 * @author April Han
 */
@Component
public class Receiver2 implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Receiver2 接收到了消息：" + new String(message.getBody()));
    }
}
