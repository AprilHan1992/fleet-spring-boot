package com.fleet.redisson.delayqueue.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author April Han
 */
@Configuration
public class RedssonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        InputStream is = new ClassPathResource("redisson-single.yml").getInputStream();
        Config config = Config.fromYAML(is);
        return Redisson.create(config);
    }
}
