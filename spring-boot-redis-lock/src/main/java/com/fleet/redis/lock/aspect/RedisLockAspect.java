package com.fleet.redis.lock.aspect;

import com.fleet.redis.lock.aspect.annotation.RedisLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author April Han
 */
@Aspect
@Component
public class RedisLockAspect {

    @Resource
    RedisLockRegistry redisLockRegistry;

    @Around("@annotation(com.fleet.redis.lock.aspect.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String name = signature.getName();
        Method method = signature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);

        Lock lock = redisLockRegistry.obtain(name);
        // 尝试获取锁，返回是否成功
        boolean isLocked = lock.tryLock(redisLock.time(), TimeUnit.SECONDS);
        Object object;
        if (isLocked) {
            object = pjp.proceed();
            lock.unlock();
        } else {
            throw new Exception("未获得锁");
        }
        return object;
    }
}
