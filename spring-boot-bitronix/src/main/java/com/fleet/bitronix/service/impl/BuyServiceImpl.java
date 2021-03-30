package com.fleet.bitronix.service.impl;

import com.fleet.bitronix.entity.Order;
import com.fleet.bitronix.service.BuyService;
import com.fleet.bitronix.service.GoodsService;
import com.fleet.bitronix.service.OrderService;
import com.fleet.bitronix.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class BuyServiceImpl implements BuyService {

    private static final Logger logger = LoggerFactory.getLogger(BuyService.class);

    @Resource
    GoodsService goodsService;

    @Resource
    OrderService orderService;

    @Resource
    UserService userService;

    @Override
    @Transactional
    public void buy(Integer userId, Integer goodsId, Integer amount, Integer money) {
        goodsService.decrease(goodsId, amount);

        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        order.setAmount(amount);
        orderService.insert(order);

        userService.pay(userId, money);
    }
}
