package com.fleet.retry.service.impl;

import com.fleet.retry.service.ReduceService;
import com.fleet.retry.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    ReduceService reduceService;

    /**
     * Propagation.REQUIRED 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
     * 1、如果外部方法没有开启事务的话，Propagation.REQUIRED 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
     * 2、如果外部方法开启事务并且被 Propagation.REQUIRED 的话，所有 Propagation.REQUIRED 修饰的内部方法和外部方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
     */

    /**
     * Retryable：标记当前方法使用重试机制
     * value：重试的触发机制，当遇到 Exception 异常的时候，会触发重试
     * maxAttempts：重试的次数（包括第一次调用，也就是说如果设置3次，调用一次后，如果一直失败触发重试，那么还当前方法还会调用2次）
     * delay：重试的延迟时间，也就是距离上一次重试方法调用的间隔，单位毫秒
     * multiplier：delay 间隔时间的倍数，也就是说，第一次重试间隔如果是2000ms，那第二次重试的时候就是2000ms 乘以这个倍数1.5，就是3000ms
     * maxDelay：重试次数之间的最大时间间隔，默认为0，即忽略；如果小于 delay 的设置，则默认为30000L
     */
    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5, maxDelay = 10000))
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transfer(String from, String to, Integer money) {
        logger.info("transfer 方法被调用，时间：" + LocalTime.now());

        add(to, money);

        reduceService.reduce(from, money);

//        Random random = new Random();
//        if (random.nextBoolean()) {
//            throw new RuntimeException("测试 transfer() 方法异常回滚");
//        }

        throw new RuntimeException("测试 transfer() 方法异常回滚");
    }

    public void add(String to, Integer money) {
        String sql = "insert into `money`(name, money) values (:name, :money) ON DUPLICATE KEY UPDATE  `money` = `money` + :money, `name` = :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", to);
        params.put("money", money);
        namedParameterJdbcTemplate.update(sql, params);
    }

    @Recover
    public void recover(Exception ex) {
        logger.info("执行回调方法" + ex.getMessage());
    }
}
