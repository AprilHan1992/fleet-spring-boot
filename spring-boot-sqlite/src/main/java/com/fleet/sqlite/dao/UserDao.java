package com.fleet.sqlite.dao;

import com.fleet.sqlite.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Insert("INSERT INTO user(name) VALUES (#{name})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'user')", before = false, keyProperty = "id", resultType = long.class)
    int insert(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int delete(Long id);

    @Update("UPDATE user SET name=#{name} WHERE id = #{id}")
    int update(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User get(Long id);

    @Select("SELECT * FROM user")
    List<User> list();
}
