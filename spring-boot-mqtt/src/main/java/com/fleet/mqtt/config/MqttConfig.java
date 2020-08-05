package com.fleet.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    /**
     * 发布的 bean 名称
     */
    public static final String SENDER_MESSAGE_CHANNEL = "senderMessageChannel";

    /**
     * 订阅的 bean 名称
     */
    public static final String RECEIVER_MESSAGE_CHANNEL = "receiverMessageChannel";

    public MqttPahoMessageDrivenChannelAdapter adapter;

    @Value("${spring.mqtt.url}")
    private String url;

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.topic}")
    private String topic;

    @Value("${spring.mqtt.sender.client-id}")
    private String senderClientId;

    @Value("${spring.mqtt.receiver.client-id}")
    private String receiverClientId;

    /**
     * Qos 对消息处理的机制
     * 0：至多一次，消息发布完全依赖底层 TCP/IP 网络。会发生消息丢失或重复。这一级别可用于如下情况，环境传感器数据，丢失一次读记录无所谓，因为不久后还会有第二次发送。
     * 1：至少一次，确保消息到达，但消息重复可能会发生。
     * 2：只有一次，确保消息到达一次。这一级别可用于如下情况，在计费系统中，消息重复或丢失会导致不正确的结果。
     */

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory mqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        // mqtt服务器url
        mqttConnectOptions.setServerURIs(url.split(","));
        // 设置连接的用户名
        mqttConnectOptions.setUserName(username);
        // 设置连接的密码
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setMaxInflight(10);
        // 设置超时时间(秒)
        mqttConnectOptions.setConnectionTimeout(30);
        // 设置会话心跳时间(秒)
        mqttConnectOptions.setKeepAliveInterval(60);
        // 每次请求是否清空连接记录
        mqttConnectOptions.setCleanSession(true);
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
        mqttConnectOptions.setWill("will", "offline".getBytes(), 2, false);
        mqttPahoClientFactory.setConnectionOptions(mqttConnectOptions);
        return mqttPahoClientFactory;
    }

    @Bean
    @ServiceActivator(inputChannel = SENDER_MESSAGE_CHANNEL)
    public MessageHandler messageHandler() {
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(senderClientId, mqttClientFactory());
        mqttPahoMessageHandler.setAsync(true);
        mqttPahoMessageHandler.setDefaultTopic(topic);
        return mqttPahoMessageHandler;
    }

    @Bean(name = SENDER_MESSAGE_CHANNEL)
    public MessageChannel senderMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer messageProducer() {
        adapter = new MqttPahoMessageDrivenChannelAdapter(receiverClientId, mqttClientFactory(), topic);
        adapter.setCompletionTimeout(30000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(receiverMessageChannel());
        return adapter;
    }

    @Bean(name = RECEIVER_MESSAGE_CHANNEL)
    public MessageChannel receiverMessageChannel() {
        return new DirectChannel();
    }
}
