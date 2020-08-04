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
    public static final String OUT_MESSAGE_CHANNEL = "outMessageChannel";

    /**
     * 订阅的 bean 名称
     */
    public static final String IN_MESSAGE_CHANNEL = "inMessageChannel";

    @Value("${spring.mqtt.url}")
    private String url;

    @Value("${spring.mqtt.client-id}")
    private String clientId;

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.topic}")
    private String topic;

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
        // 设置超时时间 单位为秒
        mqttConnectOptions.setConnectionTimeout(10);
        // 设置会话心跳时间(秒)
        mqttConnectOptions.setKeepAliveInterval(2);
        // 每次请求是否清空连接记录
        mqttConnectOptions.setCleanSession(true);
        mqttPahoClientFactory.setConnectionOptions(mqttConnectOptions);
        return mqttPahoClientFactory;
    }

    @Bean
    @ServiceActivator(inputChannel = OUT_MESSAGE_CHANNEL)
    public MessageHandler outMessageHandler() {
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(clientId + "out", mqttClientFactory());
        mqttPahoMessageHandler.setAsync(true);
        mqttPahoMessageHandler.setDefaultQos(0);
        mqttPahoMessageHandler.setDefaultRetained(false);
        mqttPahoMessageHandler.setAsyncEvents(false);
        mqttPahoMessageHandler.setDefaultTopic(topic);
        return mqttPahoMessageHandler;
    }

    @Bean
    public MessageChannel outMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inMessageHandler() {
        MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(clientId + "-in", mqttClientFactory(), topic);
        mqttPahoMessageDrivenChannelAdapter.setCompletionTimeout(300);
        mqttPahoMessageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        mqttPahoMessageDrivenChannelAdapter.setQos(1);
        mqttPahoMessageDrivenChannelAdapter.setOutputChannel(inMessageChannel());
        return mqttPahoMessageDrivenChannelAdapter;
    }

    @Bean
    public MessageChannel inMessageChannel() {
        return new DirectChannel();
    }
}
