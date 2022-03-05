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
@MapperScan(basePackages = "com.fleet.bitronix.dao.goods", sqlSessionFactoryRef = "goodsSqlSessionFactory", sqlSessionTemplateRef = "goodsSqlSessionTemplate")
public class GoodsDataSourceConfig {

    @Bean(name = "goodsDataSourceProperties")
    @ConfigurationProperties("spring.datasource.goods")
    public DataSourceProperties goodsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "goods")
    public DataSource goods(@Qualifier("goodsDataSourceProperties") DataSourceProperties goodsDataSourceProperties) throws Exception {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(goodsDataSourceProperties.getUrl());
        mysqlXADataSource.setUser(goodsDataSourceProperties.getUsername());
        mysqlXADataSource.setPassword(goodsDataSourceProperties.getPassword());

        PoolingDataSourceBean poolingDataSourceBean = new PoolingDataSourceBean();
        poolingDataSourceBean.setDataSource(mysqlXADataSource);
        poolingDataSourceBean.setBeanName("goods");
        return poolingDataSourceBean;
    }

    @Bean(name = "goodsSqlSessionFactory")
    public SqlSessionFactory goodsSqlSessionFactory(@Qualifier("goods") DataSource goods) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(goods);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/goods/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean("goodsSqlSessionTemplate")
    public SqlSessionTemplate goodsSqlSessionTemplate(@Qualifier("goodsSqlSessionFactory") SqlSessionFactory goodsSqlSessionFactory) {
        return new SqlSessionTemplate(goodsSqlSessionFactory);
    }
}
