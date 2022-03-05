package com.fleet.consul.consumer.controller;

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
        return discoveryClient.getInstances("consul-provider");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/choose")
    public String choose() {
        return loadBalancer.choose("consul-provider").getUri().toString();
    }

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://consul-provider/hello", String.class);
    }
}
