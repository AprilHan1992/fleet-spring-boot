package com.fleet.chat.qq.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Md5工具类
 *
 * @author April Han
 */
public class MD5Util {

    public static String encrypt(String content, String salt) {
        return encrypt(content + salt);
    }

    public static String encrypt(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] digest = md.digest(content.getBytes("utf-8"));
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            return null;
        }
    }
}
