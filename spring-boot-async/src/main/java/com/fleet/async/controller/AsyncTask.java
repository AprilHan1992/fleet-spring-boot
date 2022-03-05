package com.fleet.async.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

    /**
     * @Async 异步方法不要和同步调用方法写在同一个类中，否则注解时效
     */
    @Async
    public void doAsync() throws InterruptedException {
        System.out.println("执行任务开始");
        Thread.sleep(1000 * 10);
        System.out.println("执行任务结束");
    }
}
