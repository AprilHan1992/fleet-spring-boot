package com.fleet.mysql.multi.aop.config;

import com.fleet.mysql.multi.aop.enums.DBType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "master")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaver")
    @ConfigurationProperties("spring.datasource.slaver")
    public DataSource slaver() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("master") DataSource master,
                                        @Qualifier("slaver") DataSource slaver) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 设置主库数据源
        dynamicDataSource.setDefaultTargetDataSource(master);

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBType.MASTER, master);
        targetDataSources.put(DBType.SLAVER, slaver);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*Mapper.xml"));
        return bean.getObject();
    }

    public static class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DataSourceType.getDBType();
        }
    }

    public static class DataSourceType {

        private static final ThreadLocal<DBType> threadLocal = new ThreadLocal<>();

        public static void setDBType(DBType dbType) {
            System.out.println("当前数据源类型改为" + dbType);
            threadLocal.set(dbType);
        }

        public static DBType getDBType() {
            DBType dbType = threadLocal.get() == null ? DBType.MASTER : threadLocal.get();
            System.out.println("当前数据源类型为" + dbType);
            return dbType;
        }

        public static void clearDBType() {
            threadLocal.remove();
        }
    }
}
