package com.fleet.redisson.delayqueue.handle;

/**
 * @author April Han
 */
public interface DelayQueue<T> {

    void execute(T t);
}
