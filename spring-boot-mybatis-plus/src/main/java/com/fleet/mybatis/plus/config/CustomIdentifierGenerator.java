package com.fleet.mybatis.plus.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * 自定义 ID 生成器
 *
 * @author April Han
 */
@Component
public class CustomIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        // 自定义 ID 生成算法
        return 1L;
    }
}
