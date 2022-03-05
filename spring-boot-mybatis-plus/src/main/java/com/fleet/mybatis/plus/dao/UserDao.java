package com.fleet.mybatis.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fleet.mybatis.plus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    int deleteByName(String name);

    @Select("list")
    List<User> list(Page<User> page, @Param("map") Map<String, Object> map);
}
