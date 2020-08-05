package com.fleet.mqtt.controller;

import com.fleet.mqtt.config.MqttConfig;
import com.fleet.mqtt.service.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Resource
    private Sender sender;

    @Resource
    MqttConfig mqttConfig;

    @RequestMapping("/send1")
    public void send1() {
        String msg = "fleet";
        logger.info("发送消息：" + msg);
        logger.info("发送消息时间：" + new Date());
        sender.send(msg);
    }

    @RequestMapping("/send2")
    public void send2() {
        String msg = "fleet";
        logger.info("发送消息：" + msg);
        logger.info("发送消息时间：" + new Date());
        sender.send("topic1", msg);
    }

    @RequestMapping("/send3")
    public void send3() {
        String msg = "fleet";
        logger.info("发送消息：" + msg);
        logger.info("发送消息时间：" + new Date());
        sender.send("topic1", 2, msg);
    }

    /**
     * 动态添加或者删除 Topic
     */
    @RequestMapping("/topic")
    public void topic() {
        // 添加一个或多个监听Topic, 默认qos为1
        mqttConfig.adapter.addTopic("topic1");
        mqttConfig.adapter.addTopic("topic2", 1);
        mqttConfig.adapter.addTopic("topic3", "topic4");
        mqttConfig.adapter.addTopics(new String[]{"topic5", "topic6"}, new int[]{1, 1});
//        // 删除一个或多个监听Topic
//        mqttConfig.adapter.removeTopic("topic1");
//        mqttConfig.adapter.removeTopic("topic2", "topic3");
    }
}
