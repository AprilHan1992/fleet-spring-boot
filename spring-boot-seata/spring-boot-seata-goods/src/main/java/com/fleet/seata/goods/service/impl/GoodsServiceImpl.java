package com.fleet.seata.goods.service.impl;

import com.fleet.seata.goods.dao.GoodsDao;
import com.fleet.seata.goods.entity.Goods;
import com.fleet.seata.goods.service.GoodsService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private GoodsDao goodsDao;

    @Override
    public Goods get(Integer id) {
        return goodsDao.get(id);
    }

    @Override
    public boolean decrease(Integer id, Integer amount) {
        String xid = RootContext.getXID();
        logger.info("goods 服务：" + xid);

        if (goodsDao.decrease(id, amount) == 0) {
            return false;
        }
        return true;
    }
}
