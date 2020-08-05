package com.fleet.activiemq.service;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author April Han
 */
@Component
public class Sender {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(Destination destination, String msg) {
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
