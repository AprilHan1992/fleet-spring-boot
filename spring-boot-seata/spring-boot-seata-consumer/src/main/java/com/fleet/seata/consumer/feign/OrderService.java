package com.fleet.seata.consumer.feign;

import com.fleet.seata.consumer.entity.Order;
import com.fleet.seata.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-order", url = "http://127.0.0.1:8001")
public interface OrderService {

    @RequestMapping("/order/get")
    User get(@RequestParam Integer id);

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @RequestMapping("/order/insert")
    boolean insert(@RequestBody Order order);
}
