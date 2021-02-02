package com.fleet.thrift.client.client;

import com.fleet.thrift.common.gen.UserService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class ThriftClient {

    @Value("${server.thrift.host}")
    private String host;

    @Value("${server.thrift.port}")
    private Integer port;

    private UserService.Client client;

    public void start() {
        try {
            TSocket tSocket = new TSocket(host, port);
            System.out.println(tSocket.isOpen());
            TTransport tTransport = new TFramedTransport(tSocket, 600);
            tTransport.open();
            TProtocol tProtocol = new TCompactProtocol(tTransport);
            client = new UserService.Client(tProtocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public UserService.Client getClient() {
        return client;
    }

    public void setClient(UserService.Client client) {
        this.client = client;
    }
}
