package com.fleet.couchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CouchbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouchbaseApplication.class, args);
    }
}
