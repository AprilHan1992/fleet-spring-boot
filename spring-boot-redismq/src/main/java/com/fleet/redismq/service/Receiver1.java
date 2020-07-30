package com.fleet.redismq.service;

import org.springframework.stereotype.Component;

/**
 * 不继承 MessageListener
 *
 * @author April Han
 */
@Component
public class Receiver1 {

    public void receive(String msg) {
        System.out.println("Receiver1 接收到了消息：" + msg);
    }
}
