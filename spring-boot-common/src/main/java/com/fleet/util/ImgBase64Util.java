package com.fleet.util;


import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImgBase64Util {

    public static void main(String[] args) throws IOException {
        String netImgPath = "https://img-blog.csdnimg.cn/20200603122729484.png";
        String netImg2Base64DataString = NetImg2Base64Data(netImgPath);
        System.out.println("网络图片转换 Base64 Data：" + netImg2Base64DataString);

        String imgPath = "D:/Lighthouse.jpg";
        String img2Base64DataString = Img2Base64Data(imgPath);
        System.out.println("本地图片转换 Base64 Data：" + img2Base64DataString);

        String img2Base64String = Img2Base64(imgPath);
        System.out.println("本地图片转换 Base64：" + img2Base64String);

        String dest = "D:/test1.jpg";
        base642Img(img2Base64String, dest);
    }

    /**
     * 网络图片转换 Base64 字符串
     */
    private static String NetImg2Base64Data(String netImgPath) throws IOException {
        URL url = new URL(netImgPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            baos.write(b, 0, len);
        }
        is.close();
        return "data:" + connection.getContentType() + ";base64," + Base64.encodeBase64String(baos.toByteArray());
    }

    /**
     * 本地图片转换 Base64 字符串
     */
    private static String Img2Base64Data(String imgPath) throws IOException {
        InputStream is = new FileInputStream(imgPath);
        int len = is.available();
        byte[] bytes = new byte[len];
        is.read(bytes);
        is.close();
        return "data:" + Files.probeContentType(Paths.get(imgPath)) + ";base64," + Base64.encodeBase64String(bytes);
    }

    /**
     * 网络图片转换 Base64 字符串
     */
    private static String NetImg2Base64(String netImgPath) throws IOException {
        URL url = new URL(netImgPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);

        InputStream is = connection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            baos.write(b, 0, len);
        }
        is.close();
        return Base64.encodeBase64String(baos.toByteArray());
    }

    /**
     * 本地图片转换 Base64 字符串
     */
    private static String Img2Base64(String imgPath) throws IOException {
        InputStream is = new FileInputStream(imgPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) > 0) {
            baos.write(b, 0, len);
        }
        is.close();
        return Base64.encodeBase64String(baos.toByteArray());
    }

    /**
     * Base64 字符串转换本地图片
     */
    public static void base642Img(String base64String, String dest) throws IOException {
        byte[] b = Base64.decodeBase64(base64String);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        FileOutputStream fos = new FileOutputStream(dest);
        fos.write(b);
        fos.flush();
        fos.close();
    }
}
