package com.fleet.bitronix.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.bitronix.PoolingDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author April Han
 */
@Configuration
@MapperScan(basePackages = "com.fleet.bitronix.dao.order", sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderDataSourceConfig {

    @Primary
    @Bean(name = "orderDataSourceProperties")
    @ConfigurationProperties("spring.datasource.order")
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "order")
    public DataSource order(@Qualifier("orderDataSourceProperties") DataSourceProperties orderDataSourceProperties) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(orderDataSourceProperties.getUrl());
        mysqlXADataSource.setUser(orderDataSourceProperties.getUsername());
        mysqlXADataSource.setPassword(orderDataSourceProperties.getPassword());

        PoolingDataSourceBean poolingDataSourceBean = new PoolingDataSourceBean();
        poolingDataSourceBean.setDataSource(mysqlXADataSource);
        poolingDataSourceBean.setBeanName("order");
        return poolingDataSourceBean;
    }

    @Primary
    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("order") DataSource order) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(order);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/order/*Mapper.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("orderSqlSessionTemplate")
    public SqlSessionTemplate orderSqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory orderSqlSessionFactory) {
        return new SqlSessionTemplate(orderSqlSessionFactory);
    }
}
