package com.fleet.socketio.service;

import com.fleet.socketio.entity.Msg;

public interface SocketIOService {

    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息给指定客户端
     */
    void pushToUser(String userId, Msg msg);
}
