package com.fleet.seata.consumer.feign;

import com.fleet.seata.consumer.entity.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author April Han
 */
@FeignClient(value = "seata-goods", url = "http://127.0.0.1:8003")
public interface GoodsService {

    @RequestMapping("/goods/get")
    Goods get(@RequestParam Integer id);

    /**
     * 减库存
     *
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping("/goods/decrease")
    boolean decrease(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount);
}
