package com.fleet.mysql.multi.dao.master;

import com.fleet.mysql.multi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface MasterUserDao {

    int insert(User user);

    int delete(Long id);

    int update(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}
