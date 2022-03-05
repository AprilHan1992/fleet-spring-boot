package com.fleet.seata.order.service.impl;

import com.fleet.seata.order.dao.OrderDao;
import com.fleet.seata.order.entity.Order;
import com.fleet.seata.order.service.OrderService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderDao orderDao;

    @Override
    public boolean insert(Order order) {
        String xid = RootContext.getXID();
        logger.info("order 服务：" + xid);

        if (orderDao.insert(order) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Order get(Integer id) {
        return orderDao.get(id);
    }
}
