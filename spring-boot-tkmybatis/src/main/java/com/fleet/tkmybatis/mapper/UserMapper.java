package com.fleet.tkmybatis.mapper;

import com.fleet.tkmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    int update(User user);
}
