package com.fleet.socketio.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fleet.socketio.entity.Msg;
import com.fleet.socketio.service.SocketIOService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SocketIOServiceImpl implements SocketIOService {

    /**
     * 存放已连接的客户端
     */
    private static Map<String, SocketIOClient> clients = new ConcurrentHashMap<>();

    /**
     * 自定义事件 push_to_user_event，用于服务端与客户端通信
     */
    private static final String PUSH_TO_USER_EVENT = "push_to_user_event";

    /**
     * 自定义事件 receive_user_event，用于服务端与客户端通信
     */
    private static final String RECEIVE_USER_EVENT = "receive_user_event";

    @Resource
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC 容器创建之后，在加载 SocketIOServiceImpl Bean之后启动
     */
    @PostConstruct
    private void autoStartup() {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }

    @Override
    public void start() {
        socketIOServer.addConnectListener(client -> {
            String userId = getUserIdByClient(client);
            if (userId != null) {
                clients.put(userId, client);
            }
        });

        socketIOServer.addDisconnectListener(client -> {
            String userId = getUserIdByClient(client);
            if (userId != null) {
                clients.remove(userId);
                client.disconnect();
            }
        });

        socketIOServer.addEventListener(RECEIVE_USER_EVENT, String.class, (client, data, ackSender) -> {
            client.getHandshakeData();
            System.out.println("接收到客户端消息：" + data);
        });

        // 启动服务
        socketIOServer.start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushToUser(String userId, Msg msg) {
        SocketIOClient client = clients.get(userId);
        if (client != null) {
            client.sendEvent(PUSH_TO_USER_EVENT, msg);
        }
    }

    /**
     * 获取客户端 url 中的 userId 参数
     */
    private String getUserIdByClient(SocketIOClient client) {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("userId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }
}
