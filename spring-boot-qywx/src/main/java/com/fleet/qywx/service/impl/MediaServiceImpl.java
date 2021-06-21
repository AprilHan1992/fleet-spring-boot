package com.fleet.qywx.service.impl;

import com.fleet.qywx.service.MediaService;
import com.fleet.qywx.service.TokenService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author April Han
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Resource
    TokenService tokenService;

    @Override
    public String upload(String type, File media) {
        if (StringUtils.isEmpty(type) || media == null) {
            return null;
        }

        String boundary = "---------------------------" + System.currentTimeMillis();
        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            URL url = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=" + token + "&type=" + type);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            httpURLConnection.connect();

            OutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());

            String filename = media.getName();
            long fileLength = media.length();
            String contentType = new MimetypesFileTypeMap().getContentType(media);

            os.write(("--" + boundary + "\r\n" +
                    "Content-Disposition: form-data; name=\"media\"; filename=\"" + filename + "\"; filelength=" + fileLength + "\r\n" +
                    "Content-Type: " + contentType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));

            DataInputStream dis = new DataInputStream(new FileInputStream(media));
            int len;
            byte[] b = new byte[1024];
            while ((len = dis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            dis.close();

            os.write(("\r\n" + "--" + boundary + "--" + "\r\n").getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            httpURLConnection.disconnect();

            JSONObject json = JSONObject.fromObject(sb.toString());
            Integer status = (Integer) json.get("errcode");
            if (status.equals(0)) {
                return json.getString("media_id");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String uploadImg(File img) {
        if (img == null) {
            return null;
        }

        String boundary = "---------------------------" + System.currentTimeMillis();
        try {
            String token = tokenService.getToken();
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            URL url = new URL("https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            httpURLConnection.connect();

            OutputStream os = new DataOutputStream(httpURLConnection.getOutputStream());

            String filename = img.getName();
            String contentType = new MimetypesFileTypeMap().getContentType(img);

            os.write(("--" + boundary + "\r\n" +
                    "Content-Disposition: form-data; filename=\"" + filename + "\"" + "\r\n" +
                    "Content-Type: " + contentType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));

            DataInputStream dis = new DataInputStream(new FileInputStream(img));
            int len;
            byte[] b = new byte[1024];
            while ((len = dis.read(b)) != -1) {
                os.write(b, 0, len);
            }
            dis.close();

            os.write(("\r\n" + "--" + boundary + "--" + "\r\n").getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            httpURLConnection.disconnect();

            JSONObject json = JSONObject.fromObject(sb.toString());
            Integer status = (Integer) json.get("errcode");
            if (status.equals(0)) {
                return json.getString("url");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
