package com.fleet.rocketmq.controller;

import com.fleet.rocketmq.service.Receiver;
import com.fleet.rocketmq.service.Sender;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    private static final Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Resource
    private Sender sender;

    @Resource
    private Receiver receive;

    @RequestMapping("/send")
    public void send() throws InterruptedException, RemotingException, UnsupportedEncodingException, MQClientException, MQBrokerException {
        String msg = "fleet";
        logger.info("发送消息：" + msg);
        logger.info("发送消息时间：" + new Date());
        sender.send("PushTopic", "push", msg);
    }

    @RequestMapping("/receive")
    public void receive() throws MQClientException {
        receive.receive("PushTopic", "push");
    }
}
