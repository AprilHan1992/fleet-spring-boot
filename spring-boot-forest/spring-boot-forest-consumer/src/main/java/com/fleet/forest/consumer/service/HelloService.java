package com.fleet.forest.consumer.service;

import com.dtflys.forest.annotation.Request;

/**
 * @author April Han
 */
public interface HelloService {

    @Request(url = "http://localhost:8001/hello")
    String hello();
}
