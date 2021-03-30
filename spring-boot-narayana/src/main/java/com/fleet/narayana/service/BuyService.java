package com.fleet.narayana.service;

/**
 * @author April Han
 */
public interface BuyService {

    void buy(Integer userId, Integer goodsId, Integer amount, Integer money);
}
