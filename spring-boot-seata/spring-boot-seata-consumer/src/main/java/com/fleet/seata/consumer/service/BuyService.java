package com.fleet.seata.consumer.service;

import com.fleet.seata.consumer.entity.Order;
import com.fleet.seata.consumer.feign.GoodsService;
import com.fleet.seata.consumer.feign.OrderService;
import com.fleet.seata.consumer.feign.UserService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class BuyService {

    private static final Logger logger = LoggerFactory.getLogger(BuyService.class);

    @Resource
    GoodsService goodsService;

    @Resource
    OrderService orderService;

    @Resource
    UserService userService;

    @GlobalTransactional
    public void buy(Integer userId, Integer goodsId, Integer amount, Integer money) {
        String xid = RootContext.getXID();
        logger.info("consumer 服务：" + xid);

        try {
            goodsService.decrease(goodsId, amount);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        order.setAmount(amount);
        try {
            orderService.insert(order);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        try {
            userService.pay(userId, money);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
