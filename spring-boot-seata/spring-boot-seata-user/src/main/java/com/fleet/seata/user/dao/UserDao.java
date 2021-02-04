package com.fleet.seata.user.dao;

import com.fleet.seata.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface UserDao {

    User get(Integer id);

    int pay(@Param("id") Integer id, @Param("money") Integer money);
}
