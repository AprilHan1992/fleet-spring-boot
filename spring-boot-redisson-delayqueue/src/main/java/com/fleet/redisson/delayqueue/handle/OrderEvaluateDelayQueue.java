package com.fleet.redisson.delayqueue.handle;

import com.fleet.redisson.delayqueue.controller.OrderController;
import com.fleet.redisson.delayqueue.entity.Order;
import com.fleet.redisson.delayqueue.sevice.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单支付超时处理
 *
 * @author April Han
 */
@Component
@Service("orderEvaluateDelayQueue")
public class OrderEvaluateDelayQueue implements DelayQueue<String> {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    OrderService orderService;

    @Override
    public void execute(String id) {
        Order order = orderService.get(id);
        if (order != null) {
            if (StringUtils.isEmpty(order.getEvaluate())) {
                orderService.evaluate(id, "好评");
            } else {
                logger.info("订单id：" + id + "，已" + order.getEvaluate());
            }
        }
    }
}
