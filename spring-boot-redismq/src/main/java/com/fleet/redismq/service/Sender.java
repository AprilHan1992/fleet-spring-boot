package com.fleet.redismq.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Component
public class Sender {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void send(String msg) {
        redisTemplate.convertAndSend("PushTopic", msg);
    }
}
