package com.fleet.jdbc.service.impl;

import com.fleet.jdbc.entity.User;
import com.fleet.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 具名参数
     */
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(User user) {
//        // 方式一
//        String sql = "insert into `user`(`name`) values (?)";
//        return jdbcTemplate.update(sql, user.getName());

//        // 方式二
//        String sql = "insert into `user`(`name`) values (?)";
//        return jdbcTemplate.update(sql, new Object[]{user.getName()});

//        // 方式三
//        String sql = "insert into `user`(`name`) values (:name)";
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", user.getName());
//        return namedParameterJdbcTemplate.update(sql, params);

//        // 方式四
//        String sql = "insert into `user`(`name`) values (:name)";
//        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
//        return namedParameterJdbcTemplate.update(sql, paramSource);

        // 方式五（获取新增主键）
        String sql = "insert into `user`(`name`) values (:name)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int c = namedParameterJdbcTemplate.update(sql, paramSource, generatedKeyHolder);
        int id = Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
        logger.info("新增主键：" + id);
        return c;
    }

    @Override
    public int delete(Long id) {
        // 方式一
        String sql = "delete from `user` where `id` = ?";
        return jdbcTemplate.update(sql, id);

//        // 方式二
//        String sql = "delete from `user` where `id` = ?";
//        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int[] deletes(Long[] ids) {
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long id : ids) {
            batchArgs.add(new Object[]{id});
        }
        String sql = "delete from `user` where `id` = ?";
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public int update(User user) {
        String sql = "update `user` set `name` = ? where `id` = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    @Override
    public User get(Long id) {
        String sql = "select `id`, `name` from `user` where `id` = ?";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public List<User> list() {
        String sql = "select `id`, `name` from `user`";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public int count() {
        String sql = "select count(`id`) from `user`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
