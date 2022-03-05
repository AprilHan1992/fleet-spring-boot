package com.fleet.mybatis.pagehelper.dao;

import com.fleet.mybatis.pagehelper.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    Integer insert(User user);

    Integer delete(User user);

    Integer update(User user);

    User get(User user);

    List<User> list(Map<String, Object> map);
}
