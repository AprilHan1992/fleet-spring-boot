package com.fleet.gemfire.config;

import com.fleet.gemfire.entity.User;
import org.apache.geode.cache.GemFireCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;

/**
 * @author April Han
 */
@Configuration
public class GemfireConfig {

    @Bean("cache")
    CacheFactoryBean cache() {
        return new CacheFactoryBean();
    }

    @Bean
    ReplicatedRegionFactoryBean<Long, User> userRegion(@Qualifier("cache") GemFireCache cache) {
        ReplicatedRegionFactoryBean<Long, User> userRegion = new ReplicatedRegionFactoryBean<>();
        userRegion.setCache(cache);
        userRegion.setName("user");
        return userRegion;
    }
}
