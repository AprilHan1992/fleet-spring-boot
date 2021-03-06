package com.fleet.atomikos.dao.user;

import com.fleet.atomikos.entity.User;
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
