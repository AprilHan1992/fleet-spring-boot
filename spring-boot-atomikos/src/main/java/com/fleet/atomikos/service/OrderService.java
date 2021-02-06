package com.fleet.atomikos.service;

import com.fleet.atomikos.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface OrderService {

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    boolean insert(Order order);

    Order get(Integer id);
}
