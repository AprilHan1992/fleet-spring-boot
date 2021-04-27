package com.fleet.chat.turing.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 连接图灵机器人聊天
 *
 * @author April Han
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private Session session;

    /**
     * 图灵机器人配置
     */
    private final static String API_KEY = "ff317f5929904f9ea63b14fbbcdd46f4";
    private final static String API_SECRET = "aed8b3ba6afba0ca";
    private final static String API_URL = "http://openapi.tuling123.com/openapi/api/v2";

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
        String reqMessage = getReqMessage(message);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String body = restTemplate.postForObject(API_URL, reqMessage, String.class);
        logger.info("返回结果：" + body);

        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    public String getReqMessage(String message) {
        // 请求json，里面包含reqType，perception，userInfo
        Map<String, Object> map = new HashMap<>();
        // 输入类型：0-文本(默认)、1-图片、2-音频
        int reqType = 0;
        map.put("reqType", reqType);

        // 输入信息,里面包含inputText、inputImage、selfInfo
        Map<String, Object> perception = new HashMap<>();
        // 输入的文本信息
        Map<String, Object> inputText = new HashMap<>();
        inputText.put("text", message);
        perception.put("inputText", inputText);
        map.put("perception", perception);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("apiKey", API_KEY);
        userInfo.put("userId", "admin");
        map.put("userInfo", userInfo);

        return JSON.toJSONString(map);
    }

    /**
     * 发送信息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
