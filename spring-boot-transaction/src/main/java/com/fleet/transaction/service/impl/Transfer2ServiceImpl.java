package com.fleet.transaction.service.impl;

import com.fleet.transaction.service.Reduce2Service;
import com.fleet.transaction.service.Transfer2Service;
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
public class Transfer2ServiceImpl implements Transfer2Service {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    Reduce2Service reduce2Service;

    /**
     * Propagation.NESTED 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于 TransactionDefinition.PROPAGATION_REQUIRED。
     * 1、在外部方法未开启事务的情况下 Propagation.NESTED 和 Propagation.REQUIRED 作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
     * 2、如果外部方法开启事务的话，Propagation.NESTED 修饰的内部方法属于外部事务的子事务，外部主事务回滚的话，子事务也会回滚，而内部子事务可以单独回滚而不影响外部主事务和其他子事务。
     * 如果 transfer() 回滚的话，reduce() 和 reduce1()都要回滚，而 reduce() 回滚的话，并不会造成 transfer() 和 reduce1()回滚。
     */

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transfer(String from, String to, Integer money) {
        add(to, money);

        try {
            // 如果失败，回滚到它执行之前的 SavePoint，不产生脏数据
            reduce2Service.reduce(from, money);
        } catch (Exception e) {
            // 执行其他业务
            reduce2Service.reduce1(from, money);
        }

//        throw new RuntimeException("测试 transfer() 方法异常回滚");
    }


    public void add(String to, Integer money) {
        String sql = "insert into `money`(name, money) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` + :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", to);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
