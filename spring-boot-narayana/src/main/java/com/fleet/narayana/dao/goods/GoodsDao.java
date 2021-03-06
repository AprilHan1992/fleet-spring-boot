package com.fleet.narayana.dao.goods;

import com.fleet.narayana.entity.Goods;
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
