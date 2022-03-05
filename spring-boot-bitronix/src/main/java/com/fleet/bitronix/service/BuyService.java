package com.fleet.bitronix.service;

/**
 * @author April Han
 */
public interface BuyService {

    void buy(Integer userId, Integer goodsId, Integer amount, Integer money);
}
