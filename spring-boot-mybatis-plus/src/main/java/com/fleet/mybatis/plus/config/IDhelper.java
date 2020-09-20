package com.fleet.mybatis.plus.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * 分布式 ID 生成
 *
 * @author April Han
 */
public class IDhelper {

    public static Long getNextId() {
        IdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
        Number id = identifierGenerator.nextId(new Object());
        return id.longValue();
    }
}
