package com.fleet.redis.lock.controller;

import com.fleet.redis.lock.aspect.annotation.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/redis/lock")
public class RedisLockController {

    private static final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

    @Resource
    RedisLockRegistry redisLockRegistry;

    private int lockNum = 20;

    private int unLockNum = 20;

    @RequestMapping(path = "/testUnLock")
    public void testUnLock() {
        String s = Thread.currentThread().getName();
        if (unLockNum > 0) {
            logger.info(s + "抢号成功，号码是：" + unLockNum);
            unLockNum--;
        } else {
            logger.info(s + "抢号失败，号码已经被抢光");
        }
    }

    @RedisLock(time = 2)
    @RequestMapping(path = "/testLock")
    public void testLock() throws InterruptedException {
        // 模拟业务处理时间
        Thread.sleep(500);
        String s = Thread.currentThread().getName();
        if (unLockNum > 0) {
            logger.info(s + "抢号成功，号码是：" + unLockNum);
            unLockNum--;
        } else {
            logger.info(s + "抢号失败，号码已经被抢光");
        }
    }
}
