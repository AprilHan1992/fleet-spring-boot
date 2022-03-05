package com.fleet.seata.goods.controller;

import com.fleet.seata.goods.entity.Goods;
import com.fleet.seata.goods.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/get")
    public Goods get(@RequestParam Integer id) {
        return goodsService.get(id);
    }

    /**
     * 减库存
     *
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping("/decrease")
    public boolean decrease(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("减库存失败");
        }
        return goodsService.decrease(id, amount);
    }
}
