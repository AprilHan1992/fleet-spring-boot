package com.fleet.atomikos.service.impl;

import com.fleet.atomikos.dao.goods.GoodsDao;
import com.fleet.atomikos.entity.Goods;
import com.fleet.atomikos.service.GoodsService;
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
