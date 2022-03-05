package com.fleet.map.service;

import net.sf.json.JSONArray;
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
public class GaoDeService {

    @Value("${gaode.key}")
    private String key;

    /**
     * 通过经纬度获取地址信息
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public String regeo(Double longitude, Double latitude) throws IOException {
        String location = longitude + "," + latitude;
        URL url = new URL("https://restapi.amap.com/v3/geocode/regeo?key=" + key + "&location=" + location);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JSONObject json = JSONObject.fromObject(sb.toString());
        String status = (String) json.get("status");
        if ("1".equals(status)) {
            JSONObject regeocode = (JSONObject) json.get("regeocode");
            return regeocode.getString("formatted_address");
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
        URL url = new URL("https://restapi.amap.com/v3/geocode/geo?key=" + key + "&address=" + address);
        URLConnection urlConnection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JSONObject json = JSONObject.fromObject(sb.toString());
        String status = (String) json.get("status");
        if ("1".equals(status)) {
            JSONArray geocodes = (JSONArray) json.get("geocodes");
            if (geocodes.size() != 0) {
                JSONObject geocode = (JSONObject) geocodes.get(0);
                String[] locations = geocode.getString("location").split(",");

                Map<String, Double> map = new HashMap<>();
                Double longitude = Double.parseDouble(locations[0]);
                Double latitude = Double.parseDouble(locations[1]);
                map.put("longitude", longitude);
                map.put("latitude", latitude);
                return map;
            }
        }
        return null;
    }
}
