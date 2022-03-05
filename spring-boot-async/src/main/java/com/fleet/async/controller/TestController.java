package com.fleet.async.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private AsyncTask asyncTask;

    @RequestMapping("start")
    public void start() throws Exception {
        System.out.println("任务开始");
        asyncTask.doAsync();
        System.out.println("任务结束");
    }

    @RequestMapping("start1")
    public void start1() throws Exception {
        System.out.println("任务开始");
        doAsync();
        System.out.println("任务结束");
    }

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
