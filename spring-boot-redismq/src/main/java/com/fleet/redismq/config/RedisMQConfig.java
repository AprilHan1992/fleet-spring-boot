package com.fleet.redismq.config;

import com.fleet.redismq.service.Receiver1;
import com.fleet.redismq.service.Receiver2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author April Han
 */
@Configuration
public class RedisMQConfig {

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListenerAdapter(new Receiver1(), "receive"), new PatternTopic("PushTopic"));
        container.addMessageListener(new MessageListenerAdapter(new Receiver2()), new PatternTopic("PushTopic"));
        return container;
    }
}
