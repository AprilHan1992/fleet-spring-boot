package com.fleet.transaction.service.impl;

import com.fleet.transaction.service.Reduce2Service;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class Reduce2ServiceImpl implements Reduce2Service {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void reduce(String from, Integer money) {
        String sql = "insert into `money`(`name`, `money`) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` - :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", from);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);

        throw new RuntimeException("测试 reduce() 方法异常回滚");
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void reduce1(String from, Integer money) {
        String sql = "insert into `money`(`name`, `money`) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` - :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", from);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
