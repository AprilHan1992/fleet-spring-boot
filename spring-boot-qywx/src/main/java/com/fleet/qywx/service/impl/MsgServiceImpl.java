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
import java.util.List;
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
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendImageMsg(String touser, String toparty, String totag, String imageMediaId) {
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
        map.put("msgtype", "image");
        map.put("agentid", agentId);

        Map<String, Object> image = new HashMap<>();
        image.put("media_id", imageMediaId);
        map.put("image", image);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendVoiceMsg(String touser, String toparty, String totag, String voiceMediaId) {
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
        map.put("msgtype", "voice");
        map.put("agentid", agentId);

        Map<String, Object> voice = new HashMap<>();
        voice.put("media_id", voiceMediaId);
        map.put("voice", voice);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendVideoMsg(String touser, String toparty, String totag, String videoMediaId, String title, String description) {
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
        map.put("msgtype", "video");
        map.put("agentid", agentId);

        Map<String, Object> video = new HashMap<>();
        video.put("media_id", videoMediaId);
        if (StringUtils.isNotEmpty(title)) {
            video.put("title", title);
        }
        if (StringUtils.isNotEmpty(description)) {
            video.put("description", description);
        }
        map.put("video", video);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendFileMsg(String touser, String toparty, String totag, String fileMediaId) {
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
        map.put("msgtype", "file");
        map.put("agentid", agentId);

        Map<String, Object> file = new HashMap<>();
        file.put("media_id", fileMediaId);
        map.put("file", file);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendTextCardMsg(String touser, String toparty, String totag, String title, String description, String url) {
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
        map.put("msgtype", "textcard");
        map.put("agentid", agentId);

        Map<String, Object> textcard = new HashMap<>();
        textcard.put("title", title);
        textcard.put("description", description);
        textcard.put("url", url);
        map.put("textcard", textcard);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendNewsMsg(String touser, String toparty, String totag, List<Map<String, Object>> articles) {
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
        map.put("msgtype", "news");
        map.put("agentid", agentId);

        Map<String, Object> news = new HashMap<>();
        news.put("articles", articles);
        map.put("news", news);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendMarkdownMsg(String touser, String toparty, String totag, String content) {
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
        map.put("msgtype", "markdown");
        map.put("agentid", agentId);

        Map<String, Object> markdown = new HashMap<>();
        markdown.put("content", content);
        map.put("markdown", markdown);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public boolean sendInteractiveTaskCardMsg(String touser, String toparty, String totag, String title, String description, String url, String taskId, List<Map<String, Object>> btn) {
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
        map.put("msgtype", "interactive_taskcard");
        map.put("agentid", agentId);

        Map<String, Object> interactiveTaskCard = new HashMap<>();
        interactiveTaskCard.put("title", title);
        interactiveTaskCard.put("description", description);
        interactiveTaskCard.put("url", url);
        interactiveTaskCard.put("task_id", taskId);
        interactiveTaskCard.put("btn", btn);
        map.put("interactive_taskcard", interactiveTaskCard);

        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            URL msgSendUrl = new URL("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token);
            HttpURLConnection connection = (HttpURLConnection) msgSendUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(JSON.toJSONString(map));
            printWriter.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
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
