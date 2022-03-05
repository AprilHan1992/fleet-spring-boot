package com.fleet.seata.order.dao;

import com.fleet.seata.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface OrderDao {

    int insert(Order order);

    Order get(Integer id);
}
