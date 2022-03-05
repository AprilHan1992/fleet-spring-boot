package com.fleet.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebMvc
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 点对点应配置一个 /user 消息代理，广播式应配置一个 /topic 消息代理，,群发（mass），单独聊天（alone）
        registry.enableSimpleBroker("/topic", "/user", "/mass", "/alone");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是 /user
        registry.setUserDestinationPrefix("/user");
    }
}
