package com.fleet.artemis.service;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author April Han
 */
@Component
@EnableJms
public class Sender {

    @Resource
    private JmsTemplate jmsTemplate;

    public void send(Destination destination, String msg) {
        jmsTemplate.convertAndSend(destination, msg);
    }
}
