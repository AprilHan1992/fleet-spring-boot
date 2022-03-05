package com.fleet.netty.client;

import com.fleet.netty.client.config.NettyClient;
import com.fleet.netty.client.config.NettyConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author April Han
 */
@SpringBootApplication
public class NettyClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
    }

    @Resource
    NettyConfig nettyConfig;

    @Override
    public void run(String... args) throws Exception {
        NettyClient client = new NettyClient();
        InetSocketAddress socketAddress = new InetSocketAddress(nettyConfig.getHost(), nettyConfig.getPort());
        client.start(socketAddress);
    }
}
