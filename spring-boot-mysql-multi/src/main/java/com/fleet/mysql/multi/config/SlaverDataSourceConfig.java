package com.fleet.mysql.multi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author April Han
 */
@Configuration
@MapperScan(basePackages = "com.fleet.mysql.multi.dao.slaver", sqlSessionFactoryRef = "slaverSqlSessionFactory")
public class SlaverDataSourceConfig {

    @Bean(name = "slaver")
    @ConfigurationProperties("spring.datasource.slaver")
    public DataSource slaver() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaverSqlSessionFactory")
    public SqlSessionFactory slaverSqlSessionFactory(@Qualifier("slaver") DataSource slaver) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(slaver);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/slaver/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean("slaverSqlSessionTemplate")
    public SqlSessionTemplate slaverSqlSessionTemplate(@Qualifier("slaverSqlSessionFactory") SqlSessionFactory slaverSqlSessionFactory) {
        return new SqlSessionTemplate(slaverSqlSessionFactory);
    }
}
