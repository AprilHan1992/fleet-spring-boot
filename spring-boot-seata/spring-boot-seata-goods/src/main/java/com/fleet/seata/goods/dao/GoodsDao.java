package com.fleet.seata.goods.dao;

import com.fleet.seata.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface GoodsDao {

    Goods get(Integer id);

    int decrease(@Param("id") Integer id, @Param("amount") Integer amount);
}
