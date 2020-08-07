package com.fleet.xxl.job.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class XxlJobTask {

    private static final Logger logger = LoggerFactory.getLogger(XxlJobTask.class);

    /**
     * 简单任务示例（Bean模式）
     */
    @XxlJob("simpleJobHandler")
    public ReturnT<String> simpleJobHandler(String param) {
        logger.info("简单任务执行时间：" + System.currentTimeMillis());
        return ReturnT.SUCCESS;
    }

    /**
     * 分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public ReturnT<String> shardingJobHandler(String param) throws Exception {
        logger.info("分片广播任务执行时间：" + System.currentTimeMillis());
        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        int index = shardingVO.getIndex();
        int total = shardingVO.getTotal();
        logger.info("分片参数：当前分片序号 = {}, 总分片数 = {}", index, total);
        // 业务逻辑
        for (int i = 0; i < total; i++) {
            if (i == index) {
                logger.info("第 {} 片, 命中分片开始处理", i);
            } else {
                logger.info("第 {} 片, 忽略", i);
            }
        }
        return ReturnT.SUCCESS;
    }
}
