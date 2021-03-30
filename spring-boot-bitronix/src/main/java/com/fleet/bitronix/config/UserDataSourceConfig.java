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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author April Han
 */
@Configuration
@MapperScan(basePackages = "com.fleet.bitronix.dao.user", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties("spring.datasource.user")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "user")
    public DataSource user(@Qualifier("userDataSourceProperties") DataSourceProperties userDataSourceProperties) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(userDataSourceProperties.getUrl());
        mysqlXADataSource.setUser(userDataSourceProperties.getUsername());
        mysqlXADataSource.setPassword(userDataSourceProperties.getPassword());

        PoolingDataSourceBean poolingDataSourceBean = new PoolingDataSourceBean();
        poolingDataSourceBean.setDataSource(mysqlXADataSource);
        poolingDataSourceBean.setBeanName("user");
        return poolingDataSourceBean;
    }

    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("user") DataSource user) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(user);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/user/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean("userSqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory userSqlSessionFactory) {
        return new SqlSessionTemplate(userSqlSessionFactory);
    }
}
