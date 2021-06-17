package com.fleet.qywx.service.impl;

import com.alibaba.fastjson.JSON;
import com.fleet.qywx.service.MsgService;
import com.fleet.qywx.service.TokenService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Resource
    TokenService tokenService;

    @Value("${qywx.agent-id}")
    private Integer agentId;

    @Override
    public boolean sendTextMsg(String touser, String toparty, String totag, String content) {
        if (StringUtils.isEmpty(touser) && StringUtils.isEmpty(toparty) && StringUtils.isEmpty(totag)) {
            return false;
        }

        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotEmpty(touser)) {
            map.put("touser", touser);
        }
        if (StringUtils.isNotEmpty(toparty)) {
            map.put("toparty", toparty);
        }
        if (StringUtils.isNotEmpty(totag)) {
            map.put("totag", totag);
        }
        map.put("msgtype", "text");
        map.put("agentid", agentId);

        Map<String, Object> text = new HashMap<>();
        text.put("content", content);
        map.put("text", text);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL url = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();

            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            JSONObject json = JSONObject.fromObject(sb.toString());
            Integer status = (Integer) json.get("errcode");
            if (status.equals(0)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
