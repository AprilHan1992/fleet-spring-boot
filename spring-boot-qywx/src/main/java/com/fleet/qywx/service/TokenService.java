package com.fleet.qywx.service;

import java.io.IOException;

/**
 * token 操作
 *
 * @author April Han
 */
public interface TokenService {

    /**
     * 获取 access_token
     * 开发者需要缓存 access_token，用于后续接口的调用（注意：不能频繁调用 gettoken 接口，否则会受到频率拦截）。当 access_token 失效或过期时，需要重新获取。
     */
    String getToken() throws IOException;
}
