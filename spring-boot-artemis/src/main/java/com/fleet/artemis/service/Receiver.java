package com.fleet.artemis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author April Han
 */
@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "test.queue")
    public void receive(String msg) {
        logger.info("接收消息：" + msg);
        logger.info("接收消息时间：" + new Date());
    }
}
