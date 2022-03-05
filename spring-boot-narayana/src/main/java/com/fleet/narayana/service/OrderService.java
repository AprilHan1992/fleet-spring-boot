package com.fleet.narayana.service;

import com.fleet.narayana.entity.Order;
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
