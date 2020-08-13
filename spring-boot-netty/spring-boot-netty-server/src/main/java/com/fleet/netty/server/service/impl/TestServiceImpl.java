package com.fleet.netty.server.service.impl;

import com.fleet.netty.server.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author April Han
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void test() {
        System.out.println("调用 service 服务");
    }
}
