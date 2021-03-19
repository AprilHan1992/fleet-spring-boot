package com.fleet.postgis.dao;

import com.fleet.postgis.entity.Gis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author April Han
 */
@Mapper
public interface GisDao {

    Integer insert(Gis gis);

    Gis get(Long id);

    List<Gis> list();

    /**
     * 圆形区域查找
     * 以给定点为原型，radius 为半径的区域中查找满足条件的元素集合
     *
     * @param geometry 给定点
     * @param radius   区域半径，单位为 m
     * @return
     */
    List<Gis> circle(@Param("geometry") String geometry, @Param("radius") Double radius);

    List<Gis> polygon(@Param("geometry") String geometry);
}
