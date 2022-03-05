package com.fleet.chat.qingyunke.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 连接青云客机器人聊天
 *
 * @author April Han
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private Session session;

    /**
     * 青云客机器人配置
     */
    private final static String API_URL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    /**
     * 连接建立成功调用
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        logger.info(message);
        sendMessage("收到的消息：" + message);
        JSONObject jsonObject = getResMessage(message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        onClose();
        error.printStackTrace();
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
    }

    /**
     * 获取机器人返回信息
     */
    public JSONObject getResMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String body = restTemplate.getForObject(API_URL + message, String.class);
        logger.info("返回结果：" + body);

        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    /**
     * 发送信息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
