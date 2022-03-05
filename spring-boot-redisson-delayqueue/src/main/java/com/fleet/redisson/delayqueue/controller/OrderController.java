package com.fleet.redisson.delayqueue.controller;

import com.fleet.redisson.delayqueue.entity.Order;
import com.fleet.redisson.delayqueue.enums.DelayQueueEnum;
import com.fleet.redisson.delayqueue.sevice.OrderService;
import com.fleet.redisson.delayqueue.util.DelayQueueUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderService orderService;

    @Resource
    private DelayQueueUtil delayQueueUtil;

    /**
     * 新增订单
     */
    @RequestMapping("/insert")
    public Order insert(@RequestBody Order order) {
        order.setPaid("支付中");
        if (orderService.insert(order)) {
            delayQueueUtil.add(DelayQueueEnum.ORDER_PAY_TIMEOUT.getKey(), order.getId(), 10, TimeUnit.SECONDS);
            delayQueueUtil.add(DelayQueueEnum.ORDER_EVALUATE_TIMEOUT.getKey(), order.getId(), 20, TimeUnit.SECONDS);
        }
        return order;
    }

    @RequestMapping("/get")
    public Order get(@RequestParam String id) {
        return orderService.get(id);
    }

    /**
     * 订单支付
     */
    @RequestMapping("/pay")
    public Order pay(@RequestParam("id") String id) {
        Order order = orderService.get(id);
        if (order != null) {
            order.setPaid("支付完成");
            orderService.paid(id, "支付完成");
        }
        return order;
    }

    /**
     * 订单评价
     */
    @RequestMapping("/evaluate")
    public Order evaluate(@RequestParam("id") String id, @RequestParam("evaluate") String evaluate) {
        Order order = orderService.get(id);
        if (order != null) {
            order.setEvaluate(evaluate);
            orderService.evaluate(id, evaluate);
        }
        return order;
    }
}
