package com.fleet.retry.service.impl;

import com.fleet.retry.service.ReduceService;
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
public class ReduceServiceImpl implements ReduceService {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void reduce(String from, Integer money) {
        String sql = "insert into `money`(`name`, `money`) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` - :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", from);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
