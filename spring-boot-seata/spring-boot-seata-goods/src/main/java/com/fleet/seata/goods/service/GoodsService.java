package com.fleet.seata.goods.service;

import com.fleet.seata.goods.entity.Goods;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public interface GoodsService {

    Goods get(Integer id);

    boolean decrease(Integer id, Integer amount);
}
