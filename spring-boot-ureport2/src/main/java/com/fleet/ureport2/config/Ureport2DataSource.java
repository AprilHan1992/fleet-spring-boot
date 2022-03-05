package com.fleet.ureport2.config;


import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Ureport2DataSource implements BuildinDatasource {

    private static final String NAME = "fleet-ureport";

    private static final Logger logger = LoggerFactory.getLogger(Ureport2DataSource.class);

    @Resource
    private DataSource dataSource;

    /**
     * 数据源名称
     **/
    @Override
    public String name() {
        return NAME;
    }

    /**
     * 获取连接
     **/
    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Ureport2 数据源获取连接失败！");
            e.printStackTrace();
        }
        return null;
    }
}
