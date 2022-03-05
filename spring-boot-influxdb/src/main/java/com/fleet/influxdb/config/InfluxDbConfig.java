package com.fleet.influxdb.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
@ConfigurationProperties(prefix = "spring.influx")
public class InfluxDbConfig {

    private InfluxDB influxDB;

    private String url;

    private String user;

    private String password;

    private String database;

    private String retentionPolicy;

    @Bean
    public InfluxDB influxdb() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(url, user, password);
            if (!influxDB.databaseExists(database)) {
                influxDB.createDatabase(database);
            }
            influxDB.setDatabase(database);
            influxDB.setRetentionPolicy(retentionPolicy);
            // 设置日志输出级别
            influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        }
        return influxDB;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getRetentionPolicy() {
        return retentionPolicy;
    }

    public void setRetentionPolicy(String retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
    }
}
