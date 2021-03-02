package com.fleet.webssh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Configuration
@EnableWebSocket
public class WebSSHWebSocketConfig implements WebSocketConfigurer {

    @Resource
    WebSSHWebSocketHandler webSSHWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSSHWebSocketHandler, "/webssh")
                .setAllowedOrigins("*");
    }
}
