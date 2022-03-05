package com.fleet.clickhouse.dao;

import com.fleet.clickhouse.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface UserDao {

    int insert(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
