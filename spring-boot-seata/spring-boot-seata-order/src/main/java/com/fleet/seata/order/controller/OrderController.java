package com.fleet.seata.order.controller;

import com.fleet.seata.order.entity.Order;
import com.fleet.seata.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/get")
    public Order get(@RequestParam Integer id) {
        return orderService.get(id);
    }

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @RequestMapping("/insert")
    public boolean insert(@RequestBody Order order) {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("添加订单失败");
        }
        return orderService.insert(order);
    }
}
