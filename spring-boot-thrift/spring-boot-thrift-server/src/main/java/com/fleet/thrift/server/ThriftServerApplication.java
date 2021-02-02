package com.fleet.thrift.server;

import com.fleet.thrift.server.server.ThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author April Han
 */
@SpringBootApplication
public class ThriftServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ThriftServerApplication.class, args);

        try {
            ThriftServer thriftServer = context.getBean(ThriftServer.class);
            thriftServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
