package com.fleet.rocketmq.service;

import com.fleet.rocketmq.config.RocketMQConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * @author April Han
 */
@Component
public class Sender {

    @Resource
    private RocketMQConfig rocketMQConfig;

    private DefaultMQProducer producer;

    public void send(String topic, String tags, String body) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (producer == null) {
            producer = new DefaultMQProducer(rocketMQConfig.getGroup());
            // 指定 Namesrv 地址，多个地址以 ; 隔开
            producer.setNamesrvAddr(rocketMQConfig.getNamesrvAddr());
            producer.setVipChannelEnabled(false);
        }
        producer.start();
        Message message = new Message(topic, tags, body.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = producer.send(message);
        System.out.println("发送响应：msgId:" + sendResult.getMsgId() + "，发送状态：sendStatus:" + sendResult.getSendStatus());
    }
}
