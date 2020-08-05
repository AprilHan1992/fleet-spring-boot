package com.fleet.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @author April Han
 */
@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = {"topic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> optional = Optional.ofNullable(record.value());
        if (optional.isPresent()) {
            Object message = optional.get();
            logger.info("接收消息：" + message.toString());
            logger.info("接收消息时间：" + new Date());
        }
    }
}
