package com.fleet.retrofit2.consumer.client;

import com.fleet.retrofit2.consumer.entity.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface UserClient {

    @POST("/user/insert")
    Call<Boolean> insert(@Body User user);

    @GET("/user/delete/{id}")
    Call<Boolean> delete(@Path("id") Long id);

    @POST("/user/update")
    Call<Boolean> update(@Body User user);

    @GET("/user/get/{id}")
    Call<User> get(@Path("id") Long id);

    @GET("/user/list")
    Call<List<User>> list(@QueryMap Map<String, Object> map);
}
