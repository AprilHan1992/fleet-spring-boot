package com.fleet.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Md5工具类
 */
public class MD5Util {

    public static String encrypt(String content, String salt) {
        return encrypt(content + salt);
    }

    public static String encrypt(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] digest = md.digest(content.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            return null;
        }
    }
}
