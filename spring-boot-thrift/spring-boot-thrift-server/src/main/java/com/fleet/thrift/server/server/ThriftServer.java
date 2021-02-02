package com.fleet.thrift.server.server;

import com.fleet.thrift.common.gen.UserService;
import com.fleet.thrift.server.service.UserServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Component
public class ThriftServer {

    @Value("${server.thrift.port}")
    private Integer port;

    @Value("${server.thrift.min-thread-pool}")
    private Integer minThreadPool;

    @Value("${server.thrift.max-thread-pool}")
    private Integer maxThreadPool;

    @Resource
    private UserServiceImpl userService;

    public void start() {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(port);
            THsHaServer.Args args = new THsHaServer.Args(socket)
                    .minWorkerThreads(minThreadPool)
                    .maxWorkerThreads(maxThreadPool);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TCompactProtocol.Factory());
            UserService.Processor<UserServiceImpl> processor = new UserService.Processor<>(userService);
            args.processorFactory(new TProcessorFactory(processor));
            TServer server = new THsHaServer(args);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
