package com.fleet.atomikos.service.impl;

import com.fleet.atomikos.dao.order.OrderDao;
import com.fleet.atomikos.entity.Order;
import com.fleet.atomikos.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public boolean insert(Order order) {
        if (orderDao.insert(order) == 0) {
            return false;
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("添加订单失败");
        }
        return true;
    }

    @Override
    public Order get(Integer id) {
        return orderDao.get(id);
    }
}
