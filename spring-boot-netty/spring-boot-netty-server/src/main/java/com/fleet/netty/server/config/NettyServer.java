package com.fleet.netty.server.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author April Han
 */
@Component
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public void start(InetSocketAddress address) {
        // 主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(200);
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer())
                .localAddress(address)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture future = bootstrap.bind(address).sync();
            logger.info("服务器启动开始监听端口: {}", address.getPort());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭主线程组
            bossGroup.shutdownGracefully();
            // 关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }
}
