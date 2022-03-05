package com.fleet.seata.order.service;

import com.fleet.seata.order.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface OrderService {

    boolean insert(Order order);

    Order get(Integer id);
}
