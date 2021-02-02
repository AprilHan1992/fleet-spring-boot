package com.fleet.thrift.client;

import com.fleet.thrift.client.client.ThriftClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author April Han
 */
@SpringBootApplication
public class ThriftClientApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ThriftClientApplication.class, args);

        try {
            ThriftClient thriftClient = context.getBean(ThriftClient.class);
            thriftClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
