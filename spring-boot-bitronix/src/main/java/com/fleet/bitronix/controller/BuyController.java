package com.fleet.bitronix.controller;

import com.fleet.bitronix.service.BuyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
public class BuyController {

    @Resource
    BuyService buyService;

    @RequestMapping("/buy")
    public String buy() {
        Integer userId = 1;
        Integer goodsId = 1;
        Integer amount = 1;
        Integer money = 1600;

        try {
            buyService.buy(userId, goodsId, amount, money);
        } catch (Exception e) {
            return "失败";
        }

        return "成功";
    }
}
