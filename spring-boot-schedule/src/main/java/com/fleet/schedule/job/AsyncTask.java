package com.fleet.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 异步定时任务，防止租塞主线程
 *
 * @author April Han
 */
@Configuration
@EnableAsync
public class AsyncTask {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Async
    @Scheduled(fixedDelay = 1000)
    public void task1() throws InterruptedException {
        logger.info("异步定时任务1执行时间：" + System.currentTimeMillis());
        logger.info("线程：" + Thread.currentThread().getName());
        Thread.sleep(1000 * 10);
    }

    @Async
    @Scheduled(fixedDelay = 2000)
    public void task2() {
        logger.info("异步定时任务2执行时间：" + System.currentTimeMillis());
        logger.info("线程：" + Thread.currentThread().getName());
    }
}
