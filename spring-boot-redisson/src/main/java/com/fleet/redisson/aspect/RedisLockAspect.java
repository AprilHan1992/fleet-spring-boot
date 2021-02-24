package com.fleet.redisson.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Aspect
@Component
public class RedisLockAspect {

    @Resource
    private RedissonClient redissonClient;

    @Around("@annotation(com.fleet.redisson.aspect.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String name = signature.getName();

        // 对数据进行加锁
        RLock lock = redissonClient.getLock(name);
        // 加锁
        lock.lock();
        Object object;
        try {
            object = pjp.proceed();
        } finally {
            // 解锁
            lock.unlock();
        }
        return object;
    }
}
