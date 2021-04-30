package com.fleet.redisson.delayqueue.sevice.impl;

import com.fleet.redisson.delayqueue.controller.OrderController;
import com.fleet.redisson.delayqueue.entity.Order;
import com.fleet.redisson.delayqueue.sevice.OrderService;
import com.fleet.redisson.delayqueue.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private Map<String, Order> orders = new HashMap<>();

    @Override
    public boolean insert(Order order) {
        String id = UUIDUtil.getUUID();
        logger.info("订单id：" + id + "，支付状态：" + order.getPaid());
        order.setId(id);
        orders.put(id, order);
        return true;
    }

    @Override
    public Order get(String id) {
        return orders.get(id);
    }

    @Override
    public boolean paid(String id, String paid) {
        Order order = orders.get(id);
        order.setPaid(paid);
        orders.put(id, order);
        logger.info("订单id：" + id + "，支付状态：" + order.getPaid());
        return true;
    }

    @Override
    public boolean evaluate(String id, String evaluate) {
        Order order = orders.get(id);
        order.setEvaluate(evaluate);
        orders.put(id, order);
        logger.info("订单id：" + id + "，评价：" + order.getEvaluate());
        return true;
    }
}
