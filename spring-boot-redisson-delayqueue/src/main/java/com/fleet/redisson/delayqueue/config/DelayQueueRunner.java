package com.fleet.redisson.delayqueue.config;

import com.fleet.redisson.delayqueue.enums.DelayQueueEnum;
import com.fleet.redisson.delayqueue.handle.DelayQueue;
import com.fleet.redisson.delayqueue.util.DelayQueueUtil;
import com.fleet.redisson.delayqueue.util.SpringContextUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author April Han
 */
@Component
public class DelayQueueRunner implements CommandLineRunner {

    @Resource
    private DelayQueueUtil delayQueueUtil;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();

    private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void run(String... args) {
        executorService.execute(() -> {
            while (true) {
                try {
                    DelayQueueEnum[] delayQueueEnums = DelayQueueEnum.values();
                    for (DelayQueueEnum delayQueueEnum : delayQueueEnums) {
                        String key = delayQueueEnum.getKey();
                        String beanName = delayQueueEnum.getBeanName();
                        String id = delayQueueUtil.get(key);
                        if (id == null) {
                            continue;
                        }
                        DelayQueue delayQueue = SpringContextUtil.getBean(beanName);
                        delayQueue.execute(id);
                    }
                } catch (InterruptedException e) {
                }
            }
        });
    }
}
