package com.fleet.socketio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SocketIOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketIOApplication.class, args);
    }
}
