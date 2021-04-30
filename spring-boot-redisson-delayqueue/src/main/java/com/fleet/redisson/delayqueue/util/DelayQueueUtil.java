package com.fleet.redisson.delayqueue.util;

import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author April Han
 */
@Component
public class DelayQueueUtil {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 添加延迟队列
     *
     * @param key      队列键
     * @param value    队列值
     * @param delay    延迟时间
     * @param timeUnit 时间单位
     */
    public <T> void add(String key, T value, long delay, TimeUnit timeUnit) {
        RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(key);
        RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        delayedQueue.offerAsync(value, delay, timeUnit);
    }

    /**
     * 获取延迟队列
     *
     * @param key
     */
    public <T> T get(String key) throws InterruptedException {
        RBlockingDeque<T> blockingDeque = redissonClient.getBlockingDeque(key);
        return (T) blockingDeque.take();
    }
}
