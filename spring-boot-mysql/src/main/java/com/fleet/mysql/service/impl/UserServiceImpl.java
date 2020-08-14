package com.fleet.mysql.service.impl;

import com.fleet.mysql.entity.User;
import com.fleet.mysql.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(User user) {
        // 方式一
        String sql = "insert into `user`(`name`) values (?)";
        return jdbcTemplate.update(sql, user.getName());

//        // 方式二
//        String sql = "insert into `user`(`name`) values (:name)";
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", user.getName());
//        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from `user` where `id` = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(User user) {
        String sql = "update `user` set `name` = ? where `id` = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    @Override
    public User get(Long id) {
        String sql = "select `id`, `name` from `user` where `id` = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            return user;
        }, id);
    }

    @Override
    public List<Map<String, Object>> list() {
        String sql = "select `id`, `name` from `user`";
        return jdbcTemplate.queryForList(sql);
    }
}
