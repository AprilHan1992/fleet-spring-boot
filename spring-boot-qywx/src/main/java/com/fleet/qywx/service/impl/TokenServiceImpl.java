package com.fleet.qywx.service.impl;

import com.fleet.qywx.service.TokenService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author April Han
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${qywx.corp-id}")
    private String corpId;

    @Value("${qywx.corp-secret}")
    private String corpSecret;

    @Override
    public String getToken() throws IOException {
        URL url = new URL("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        JSONObject json = JSONObject.fromObject(sb.toString());
        Integer status = (Integer) json.get("errcode");
        if (status.equals(0)) {
            return json.getString("access_token");
        }
        return null;
    }
}
