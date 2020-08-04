package com.fleet.mqtt.service;

import com.fleet.mqtt.config.MqttConfig;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = MqttConfig.OUT_MESSAGE_CHANNEL)
public interface Sender {

//    void send(String data, @Header(MqttHeaders.TOPIC) String topic);

    void send(String data);

    void send(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void send(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
