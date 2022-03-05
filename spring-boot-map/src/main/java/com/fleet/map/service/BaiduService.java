package com.fleet.map.service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class BaiduService {

    @Value("${baidu.key}")
    private String key;

    /**
     * 通过经纬度获取地址信息
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public String regeo(Double longitude, Double latitude) throws IOException {
        String location = latitude + "," + longitude;
        URL url = new URL("http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + key + "&output=json&coordtype=bd09ll&location=" + location);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JSONObject json = JSONObject.fromObject(sb.toString());
        Integer status = (Integer) json.get("status");
        if (status.equals(0)) {
            JSONObject result = (JSONObject) json.get("result");
            return result.getString("formatted_address");
        }
        return null;
    }

    /**
     * 通过地址信息获取经纬度
     *
     * @param address 地址信息
     * @return
     */
    public Map<String, Double> geo(String address) throws IOException {
        URL url = new URL("http://api.map.baidu.com/geocoding/v3/?ak=" + key + "&output=json&ret_coordtype=bd09ll&address=" + address);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JSONObject json = JSONObject.fromObject(sb.toString());
        Integer status = (Integer) json.get("status");
        if (status.equals(0)) {
            JSONObject result = (JSONObject) json.get("result");
            JSONObject location = (JSONObject) result.get("location");
            Map<String, Double> map = new HashMap<>();
            Double longitude = location.getDouble("lng");
            Double latitude = location.getDouble("lat");
            map.put("longitude", longitude);
            map.put("latitude", latitude);
            return map;
        }
        return null;
    }
}
