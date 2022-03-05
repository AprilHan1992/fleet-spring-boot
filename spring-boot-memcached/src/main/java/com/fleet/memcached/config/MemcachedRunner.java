package com.fleet.memcached.config;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author April Han
 */
@Component
public class MemcachedRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedRunner.class);

    @Resource
    private MemcachedConfig memcachedConfig;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) {
        try {
            client = new MemcachedClient(new InetSocketAddress(memcachedConfig.getIp(), memcachedConfig.getPort()));
        } catch (IOException e) {
            logger.error("init MemcachedClient failed:", e);
        }
    }

    public MemcachedClient getClient() {
        return client;
    }

    public void setClient(MemcachedClient client) {
        this.client = client;
    }
}
