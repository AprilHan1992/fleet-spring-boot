package com.fleet.redismq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 不继承 MessageListener
 *
 * @author April Han
 */
@Component
public class Receiver1 {

    private static final Logger logger = LoggerFactory.getLogger(Receiver1.class);

    public void receive(String msg) {
        logger.info("Receiver1 接收消息：" + msg);
        logger.info("Receiver1 接收消息时间：" + new Date());
    }
}
