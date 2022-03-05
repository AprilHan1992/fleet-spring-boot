package com.fleet.netty.server;

import com.fleet.netty.server.config.NettyConfig;
import com.fleet.netty.server.config.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author April Han
 */
@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Resource
    NettyConfig nettyConfig;

    @Override
    public void run(String... args) {
        NettyServer server = new NettyServer();
        InetSocketAddress address = new InetSocketAddress(nettyConfig.getHost(), nettyConfig.getPort());
        server.start(address);
    }
}
