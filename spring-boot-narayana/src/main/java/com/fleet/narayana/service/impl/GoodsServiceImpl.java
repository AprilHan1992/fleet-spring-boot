package com.fleet.narayana.service.impl;

import com.fleet.narayana.dao.goods.GoodsDao;
import com.fleet.narayana.entity.Goods;
import com.fleet.narayana.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author April Han
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public Goods get(Integer id) {
        return goodsDao.get(id);
    }

    @Override
    public boolean decrease(Integer id, Integer amount) {
        if (goodsDao.decrease(id, amount) == 0) {
            return false;
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("减库存失败");
        }
        return true;
    }
}
