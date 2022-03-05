package com.fleet.eureka.consumer.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    private LoadBalancerClient loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public List<ServiceInstance> services() {
        return discoveryClient.getInstances("eureka-provider");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/choose")
    public String choose() {
        return loadBalancer.choose("eureka-provider").getUri().toString();
    }

    @Resource
    RestTemplate loadBalanced;

    @RequestMapping("/hello")
    public String hello() {
        return loadBalanced.getForObject("http://eureka-provider/hello", String.class);
    }

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("hello1")
    public String hello1() {
        return restTemplate.getForObject(choose() + "/hello", String.class);
    }

    @RequestMapping("hello2")
    public String hello2() {
        return restTemplate.getForObject("http://localhost:8000/api/provider/hello", String.class);
    }
}
