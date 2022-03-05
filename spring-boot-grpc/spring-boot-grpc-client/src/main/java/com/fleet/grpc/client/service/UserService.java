package com.fleet.grpc.client.service;

import com.fleet.grpc.common.BooleanResponse;
import com.fleet.grpc.common.UserRequest;
import com.fleet.grpc.common.UserResponse;
import com.fleet.grpc.common.UserServiceGrpc;
import com.fleet.grpc.common.entity.User;
import io.grpc.Channel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author April Han
 */
@Service
public class UserService {

    @GrpcClient("user")
    private Channel channel;

    private UserServiceGrpc.UserServiceBlockingStub userService;

    @PostConstruct
    public void init() {
        userService = UserServiceGrpc.newBlockingStub(channel);
    }

    public boolean insert(User user) {
        BooleanResponse response = userService.insert(UserRequest.newBuilder().setId(user.getId()).setName(user.getName()).setAge(user.getAge()).build());
        return response.getBool();
    }

    public User get(Integer id) {
        UserResponse response = userService.get(UserRequest.newBuilder().setId(id).build());
        User user = new User();
        user.setId(response.getId());
        user.setName(response.getName());
        user.setAge(response.getAge());
        return user;
    }
}
