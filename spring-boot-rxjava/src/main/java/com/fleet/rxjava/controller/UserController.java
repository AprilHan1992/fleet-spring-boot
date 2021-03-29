package com.fleet.rxjava.controller;

import com.fleet.rxjava.entity.User;
import com.fleet.rxjava.json.R;
import com.fleet.rxjava.service.UserService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/insert")
    public Single<ResponseEntity<User>> insert(@RequestBody User user) {
        return userService.insert(user)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @RequestMapping("/delete/{id}")
    public Single<ResponseEntity<R>> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(R.ok()));
    }

    @RequestMapping("update")
    public Single<ResponseEntity<User>> update(@RequestBody User user) {
        return userService.update(user)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @RequestMapping("/get/{id}")
    public Single<ResponseEntity<User>> getUserDetail(@PathVariable(value = "id") Long id) {
        return userService.get(id)
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }

    @RequestMapping("list")
    public Single<ResponseEntity<List<User>>> list() {
        return userService.list()
                .subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
}
