package com.fleet.retrofit2.consumer.service;

import com.fleet.retrofit2.consumer.client.UserClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserClient userClient;
}
