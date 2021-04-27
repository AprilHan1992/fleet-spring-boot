package com.fleet.chat.qq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fleet.chat.qq.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 连接QQ机器人聊天
 *
 * @author April Han
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private Session session;

    /**
     * QQ机器人配置
     */
    private final static Integer APP_ID = 2124858249;
    private final static String APP_KEY = "gehYdjjrkrzJ1KhU";
    private final static String API_URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

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
    public void onMessage(String message) throws Exception {
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
    public JSONObject getResMessage(String message) throws Exception {
        String str = getReqMessage(message);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String body = restTemplate.getForObject(API_URL + "?" + str, String.class);
        logger.info("返回结果：" + body);

        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    public String getReqMessage(String message) throws Exception {
        // 请求json
        Map<String, Object> map = new HashMap<>();
        map.put("app_id", APP_ID);
        map.put("time_stamp", new Date().getTime() / 1000);
        String randomString = getRandomString(10);
        map.put("nonce_str", randomString);
        map.put("session", "admin");
        map.put("question", message);

        String str = "";
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null && !value.equals("")) {
                str += key + "=" + value + "&";
            }
        }
        str += "&sign=" + getReqSign(map);
        return str;
    }

    /**
     * 发送信息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public String getReqSign(Map<String, Object> map) throws Exception {
        Collection<String> keySet = map.keySet();
        List<String> list = new ArrayList<>(keySet);

        // 1. 对key键值按字典升序排序
        Collections.sort(list);

        // 2. 拼按URL键值对
        String str = "";
        for (String key : list) {
            Object value = map.get(key);
            if (value != null && !value.equals("")) {
                str += key + "=" + URLEncoder.encode(value.toString(), "UTF-8") + "&";
            }
        }

        // 3. 拼接app_key
        str += "app_key=" + APP_KEY;

        // 4. MD5运算+转换大写，得到请求签名
        String sign = MD5Util.encrypt(str).toUpperCase();
        return sign;
    }
}
