package com.fleet.transaction.service.impl;

import com.fleet.transaction.service.Reduce1Service;
import com.fleet.transaction.service.Transfer1Service;
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
public class Transfer1ServiceImpl implements Transfer1Service {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    Reduce1Service reduce1Service;

    /**
     * Propagation.REQUIRES_NEW 创建一个新的事务，如果当前存在事务，则把当前事务挂起。也就是说不管外部方法是否开启事务，Propagation.REQUIRES_NEW 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
     * 如果 transfer() 发生异常回滚， reduce() 不会跟着回滚，因为 reduce() 开启了独立的事务
     * 如果 reduce() 抛出了未被捕获的异常并且这个异常满足事务回滚规则的话，transfer() 同样也会回滚，因为这个异常被 transfer() 的事务管理机制检测到了
     */

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transfer(String from, String to, Integer money) {
        add(to, money);

        reduce1Service.reduce(from, money);

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
