package com.fleet.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 基于注解
 *
 * @author April Han
 */
public class BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    /**
     * 表达式
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void task1() {
        logger.info("任务1执行时间：" + System.currentTimeMillis());
    }

    /**
     * 指定时间间隔，例如：5秒，fixedDelay 的间隔时间是根据上次的任务结束的时候开始计时的
     */
    @Scheduled(fixedDelay = 5000)
    private void task2() {
        logger.info("任务2执行时间：" + System.currentTimeMillis());
    }

    /**
     * 指定时间间隔，例如：5秒, fixedRate 的间隔时间是根据上次任务开始的时候计时的
     */
    @Scheduled(fixedRate = 5000)
    private void task3() {
        logger.info("任务3执行时间" + System.currentTimeMillis());
    }
}
