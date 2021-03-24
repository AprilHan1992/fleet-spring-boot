package com.fleet.file.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author April Han
 */
public class UUIDUtil {

    /**
     * 随机获取 UUID 值
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
