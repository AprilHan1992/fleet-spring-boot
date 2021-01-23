package com.fleet.elastic.job.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.fleet.elastic.job.job.ElasticJobTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Configuration
public class ElasticJobConfig {

    @Resource
    private CoordinatorRegistryCenter registryCenter;

    /**
     * cron表达式，用于控制作业触发时间
     */
    private final String cron = "0/5 * * * * ?";

    /**
     * 作业分片总数
     */
    private final int shardingTotalCount = 3;

    /**
     * 分片序列号和参数用等号分隔，多个键值对用逗号分隔
     * 分片序列号从0开始，不可大于或等于作业分片总数
     * 如：0=A,1=B,2=C
     */
    private final String shardingItemParameters = "0=A,1=B,2=C";

    /**
     * 作业自定义参数
     * 作业自定义参数，可通过传递该参数为作业调度的业务方法传参，用于实现带参数的作业
     * 例：每次获取的数据量、作业实例从数据库读取的主键等。
     */
    private final String jobParameter = "jobParameter";

    @Bean
    public ElasticJobTask elasticJobTask() {
        return new ElasticJobTask();
    }

    @Bean(initMethod = "init")
    public JobScheduler jobScheduler(final SimpleJob simpleJob) {
        LiteJobConfiguration jobConfig = liteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters, jobParameter);
        return new SpringJobScheduler(simpleJob, registryCenter, jobConfig);
    }

    private LiteJobConfiguration liteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                      final String cron,
                                                      final int shardingTotalCount,
                                                      final String shardingItemParameters,
                                                      final String jobParameter) {
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .jobParameter(jobParameter)
                .build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());
        return LiteJobConfiguration.newBuilder(simpleJobConfiguration)
                .overwrite(true)
                .build();
    }
}
