package com.fleet.clickhouse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import javax.sql.DataSource;

/**
 * @author April Han
 */
@Configuration
@ConfigurationProperties("clickhouse")
public class ClickHouseConfig {

    private String url;

    private Properties properties;

    @Bean(name = "clickHouseProperties")
    public ClickHouseProperties clickHouseProperties() {
        ClickHouseProperties clickHouseProperties = new ClickHouseProperties();
        clickHouseProperties.setDatabase(properties.getDatabase());
        clickHouseProperties.setUser(properties.getUser());
        clickHouseProperties.setPassword(properties.getPassword());
        return clickHouseProperties;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(ClickHouseProperties clickHouseProperties) {
        return new ClickHouseDataSource(url, clickHouseProperties);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Properties {

        private String database;

        private String user;

        private String password;

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
