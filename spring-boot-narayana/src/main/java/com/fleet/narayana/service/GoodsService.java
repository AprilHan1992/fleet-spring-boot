package com.fleet.narayana.service;

import com.fleet.narayana.entity.Goods;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface GoodsService {

    Goods get(Integer id);

    /**
     * 减库存
     *
     * @param id
     * @param amount
     * @return
     */
    boolean decrease(Integer id, Integer amount);
}
