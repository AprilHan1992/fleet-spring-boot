package com.fleet.elastic.job.config;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
public class ElasticJobRegistryCenterConfig {

    private final String serverLists = "localhost:2181";

    private final String namespace = "fleet";

    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter registryCenter() {
        ZookeeperConfiguration config = new ZookeeperConfiguration(serverLists, namespace);
        config.setSessionTimeoutMilliseconds(1000);
        return new ZookeeperRegistryCenter(config);
    }
}
