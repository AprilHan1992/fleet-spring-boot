package com.fleet.elastic.job.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author April Han
 */
public class ElasticJobTask implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(ElasticJobTask.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("Thread ID：" + Thread.currentThread().getId());
        logger.info("作业分片总数：" + shardingContext.getShardingTotalCount());
        logger.info("当前分片项：" + shardingContext.getShardingItem());
        logger.info("当前参数：" + shardingContext.getShardingParameter());
        logger.info("作业名称：" + shardingContext.getJobName());
        logger.info("作业自定义参数：" + shardingContext.getJobParameter());
    }
}
