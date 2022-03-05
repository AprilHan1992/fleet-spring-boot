package com.fleet.grpc.server.service;

import com.fleet.grpc.common.BooleanResponse;
import com.fleet.grpc.common.UserRequest;
import com.fleet.grpc.common.UserResponse;
import com.fleet.grpc.common.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author April Han
 */
@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void insert(UserRequest request, StreamObserver<BooleanResponse> responseObserver) {
        BooleanResponse response = BooleanResponse.newBuilder().setBool(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void get(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse response = UserResponse.newBuilder().setId(1).setName("fleet").setAge(18).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
