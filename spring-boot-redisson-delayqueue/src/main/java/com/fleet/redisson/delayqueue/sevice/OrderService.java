package com.fleet.redisson.delayqueue.sevice;

import com.fleet.redisson.delayqueue.entity.Order;

/**
 * @author April Han
 */
public interface OrderService {

    boolean insert(Order order);

    Order get(String id);

    boolean paid(String id, String paid);

    boolean evaluate(String id, String evaluate);
}
