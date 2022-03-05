package com.fleet.webssh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.webssh.constant.Constants;
import com.fleet.webssh.entity.Connection;
import com.fleet.webssh.entity.Operate;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author April Han
 */
@Component
public class WebSSHWebSocketHandler implements WebSocketHandler {

    private Connection connection;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private static final Logger logger = LoggerFactory.getLogger(WebSSHWebSocketHandler.class);

    /**
     * 与客户端完成连接后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("连接成功");
        if (connection == null) {
            connection = new Connection();
        }
        JSch jSch = new JSch();
        connection.setjSch(jSch);
        connection.setSession(session);
    }

    /**
     * 接收到客户端消息时调用
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {
            logger.info("发送命令：{}", message.toString());
            TextMessage textMessage = (TextMessage) message;
            String payload = textMessage.getPayload();

            ObjectMapper objectMapper = new ObjectMapper();
            Operate operate;
            try {
                operate = objectMapper.readValue(payload, Operate.class);
            } catch (IOException e) {
                logger.error("Json 转换异常，异常信息:{}", e.getMessage());
                return;
            }
            if (Constants.CONNECT_OPERATE.equals(operate.getOperate())) {
                // 启动线程异步处理
                executorService.execute(() -> {
                    try {
                        connect(connection, operate, session);
                    } catch (JSchException | IOException e) {
                        logger.error("连接异常，异常信息：{}", e.getMessage());
                        close();
                    }
                });
            } else if (Constants.COMMAND_OPERATE.equals(operate.getOperate())) {
                if (connection != null) {
                    try {
                        String command = operate.getCommand();
                        send(connection.getChannel(), command);
                    } catch (IOException e) {
                        logger.error("连接异常，异常信息：{}", e.getMessage());
                        close();
                    }
                }
            } else {
                logger.error("不支持的操作");
                close();
            }
        } else if (message instanceof BinaryMessage) {

        } else if (message instanceof PongMessage) {

        } else {
            System.out.println("Unexpected WebSocket message type: " + message);
        }
    }

    /**
     * 出现错误的回调
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("出现错误");
    }

    /**
     * 与客户端连接关闭的回调
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("连接关闭");
        close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 连接终端
     */
    private void connect(Connection connection, Operate operate, WebSocketSession session) throws JSchException, IOException {
        JSch jSch = connection.getjSch();
        Session s = jSch.getSession(operate.getUsername(), operate.getHost(), operate.getPort());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        s.setConfig(config);
        s.setPassword(operate.getPassword());
        s.connect(30000);
        Channel channel = s.openChannel("shell");
        channel.connect(3000);
        connection.setChannel(channel);
        try (InputStream is = channel.getInputStream()) {
            byte[] buffer = new byte[1024];
            int i;
            while ((i = is.read(buffer)) != -1) {
                send(session, Arrays.copyOfRange(buffer, 0, i));
            }
        } finally {
            s.disconnect();
            channel.disconnect();
        }
    }

    /**
     * 将终端消息返回到客户端
     */
    public void send(WebSocketSession session, byte[] buffer) throws IOException {
        session.sendMessage(new TextMessage(buffer));
    }

    /**
     * 将客户端消息发送到终端
     */
    private void send(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream os = channel.getOutputStream();
            os.write(command.getBytes());
            os.flush();
        }
    }

    public void close() {
        if (connection != null) {
            if (connection.getChannel() != null) {
                connection.getChannel().disconnect();
            }
        }
    }
}
