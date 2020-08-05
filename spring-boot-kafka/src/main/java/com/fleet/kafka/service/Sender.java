package com.fleet.kafka.service;

import com.alibaba.fastjson.JSON;
import com.fleet.kafka.entity.Msg;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author April Han
 */
@Component
public class Sender {

    @Resource
    private KafkaTemplate kafkaTemplate;

    public void send(String msg) {
        Msg message = new Msg();
        message.setId("KFK_" + System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        kafkaTemplate.send("topic", JSON.toJSONString(message));
    }
}
