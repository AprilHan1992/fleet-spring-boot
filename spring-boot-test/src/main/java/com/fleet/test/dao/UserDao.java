package com.fleet.test.dao;

import com.fleet.test.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    int insert(User user);

    User get(Long id);
}
