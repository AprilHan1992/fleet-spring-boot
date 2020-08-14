package com.fleet.transaction.service.impl;

import com.fleet.transaction.service.ReduceService;
import com.fleet.transaction.service.TransferService;
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
public class TransferServiceImpl implements TransferService {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    ReduceService reduceService;

    /**
     * Propagation.REQUIRED 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
     * 1、如果外部方法没有开启事务的话，Propagation.REQUIRED 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
     * 2、如果外部方法开启事务并且被 Propagation.REQUIRED 的话，所有 Propagation.REQUIRED 修饰的内部方法和外部方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
     */

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transfer(String from, String to, Integer money) {
        add(to, money);

        reduceService.reduce(from, money);

        throw new RuntimeException("测试 transfer() 方法异常回滚");
    }

    public void add(String to, Integer money) {
        String sql = "insert into `money`(name, money) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` + :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", to);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
