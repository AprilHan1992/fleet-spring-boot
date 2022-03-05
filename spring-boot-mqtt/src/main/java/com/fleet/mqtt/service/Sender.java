package com.fleet.mqtt.service;

import com.fleet.mqtt.config.MqttConfig;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
@MessagingGateway(defaultRequestChannel = MqttConfig.SENDER_MESSAGE_CHANNEL)
public interface Sender {

    /**
     * 发送信息
     *
     * @param payload 消息主体
     */
    void send(String payload);

    /**
     * 发送信息
     *
     * @param topic   主题
     * @param payload 消息主体
     */
    void send(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 发送信息
     *
     * @param topic   主题
     * @param qos     对消息处理的机制
     * @param payload 消息主体
     */
    void send(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
