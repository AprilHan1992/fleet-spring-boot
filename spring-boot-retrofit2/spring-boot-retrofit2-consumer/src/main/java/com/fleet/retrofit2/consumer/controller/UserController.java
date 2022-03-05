package com.fleet.retrofit2.consumer.controller;

import com.fleet.retrofit2.consumer.client.UserClient;
import com.fleet.retrofit2.consumer.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserClient userClient;

    @RequestMapping("/insert")
    public Object insert(@RequestBody User user) throws IOException {
        Call<Boolean> call = userClient.insert(user);
        Response<Boolean> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return response.errorBody();
        }
    }

    @RequestMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id) throws IOException {
        Call<Boolean> call = userClient.delete(id);
        Response<Boolean> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return response.errorBody();
        }
    }

    @RequestMapping("/update")
    public Object update(@RequestBody User user) throws IOException {
        Call<Boolean> call = userClient.update(user);
        Response<Boolean> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return response.errorBody();
        }
    }

    @RequestMapping("/get/{id}")
    public Object get(@PathVariable("id") Long id) throws IOException {
        Call<User> call = userClient.get(id);
        Response<User> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return response.errorBody();
        }
    }

    @RequestMapping("/list")
    public Object list(Map<String, Object> map) throws IOException {
        Call<List<User>> call = userClient.list(map);
        Response<List<User>> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            return response.errorBody();
        }
    }
}
