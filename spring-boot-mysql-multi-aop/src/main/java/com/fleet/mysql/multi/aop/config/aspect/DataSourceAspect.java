package com.fleet.mysql.multi.aop.config.aspect;

import com.fleet.mysql.multi.aop.config.DataSourceConfig;
import com.fleet.mysql.multi.aop.enums.DBType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("!@annotation(com.fleet.mysql.multi.aop.annotation.Master) "
            + "&& (execution(* com.fleet.mysql.multi.aop.service..*.get*(..)) "
            + "|| execution(* com.fleet.mysql.multi.aop.service..*.select*(..)) "
            + "|| execution(* com.fleet.mysql.multi.aop.service..*.list*(..)))")
    public void read() {
        DataSourceConfig.DataSourceType.setDBType(DBType.SLAVER);
    }

    @Before("@annotation(com.fleet.mysql.multi.aop.annotation.Master) "
            + "|| execution(* com.fleet.mysql.multi.aop.service..*.insert*(..)) "
            + "|| execution(* com.fleet.mysql.multi.aop.service..*.delete*(..)) "
            + "|| execution(* com.fleet.mysql.multi.aop.service..*.update*(..))")
    public void write() {
        DataSourceConfig.DataSourceType.setDBType(DBType.MASTER);
    }
}
