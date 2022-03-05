package com.fleet.activiemq.config;

import org.apache.activemq.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {

    /**
     * 消息重试配置项
     *
     * @return
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        // 是否在每次失败重发是，增长等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        // 设置重发最大拖延时间，-1 表示没有拖延，只有setUseExponentialBackOff(true)时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        // 重发次数，默认为6次
        redeliveryPolicy.setMaximumRedeliveries(10);
        // 初始重发延迟时间，默认为1秒（1000）
        redeliveryPolicy.setInitialRedeliveryDelay(0);
        // 重发延迟时间，当setInitialRedeliveryDelay(0)时生效，默认1秒（1000）
        redeliveryPolicy.setRedeliveryDelay(2000);
        // 第一次失败后重发前等待500毫秒，第二次500*2，依次递增
        redeliveryPolicy.setBackOffMultiplier(2);
        // 是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        return redeliveryPolicy;
    }

    @Bean(name = "customJmsListenerContainerFactory")
    JmsListenerContainerFactory<?> customJmsListenerContainerFactory(ConnectionFactory factory) {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setConnectionFactory(factory);
        simpleJmsListenerContainerFactory.setPubSubDomain(true);
        return simpleJmsListenerContainerFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        // 设置持久化（1 ：非持久， 2 ：持久化）
        jmsTemplate.setDeliveryMode(2);
        jmsTemplate.setConnectionFactory(factory);
        // 消息确认模式（0：事物提交并确认，1：自动确认，2：客户端手动确认，3：自动批量确认，4：单条消息确认（activemq 独有））
        jmsTemplate.setSessionAcknowledgeMode(4);
        return jmsTemplate;
    }
}
