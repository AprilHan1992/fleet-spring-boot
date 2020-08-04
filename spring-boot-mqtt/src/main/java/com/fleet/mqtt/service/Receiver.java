package com.fleet.mqtt.service;

import com.fleet.mqtt.config.MqttConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Bean
    @ServiceActivator(inputChannel = MqttConfig.IN_MESSAGE_CHANNEL)
    public MessageHandler handler() {
        return message -> {
            logger.info("接收消息：" + message.getPayload().toString());
            logger.info("接收消息时间：" + new Date());
        };
    }
}
