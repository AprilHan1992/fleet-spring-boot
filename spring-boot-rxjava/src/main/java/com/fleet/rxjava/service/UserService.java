package com.fleet.rxjava.service;

import com.fleet.rxjava.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author April Han
 */
@Service
public class UserService {

    private Map<Long, User> data = new ConcurrentHashMap<>();

    public Single<User> insert(User user) {
        return Single.create(singleSubscriber -> {
            data.put(user.getId(), user);
            singleSubscriber.onSuccess(user);
        });
    }

    public Completable delete(Long id) {
        return Completable.create(completableSubscriber -> {
            data.remove(id);
            completableSubscriber.onComplete();
        });
    }

    public Single<User> update(User user) {
        return Single.create(singleSubscriber -> {
            data.put(user.getId(), user);
            singleSubscriber.onSuccess(user);
        });
    }

    public Single<User> get(Long id) {
        return Single.create(singleSubscriber -> {
            singleSubscriber.onSuccess(data.get(id));
        });
    }

    public Single<List<User>> list() {
        return Single.create(singleSubscriber -> {
            List<User> list = new ArrayList<>();
            for (Long id : data.keySet()) {
                list.add(data.get(id));
            }
            singleSubscriber.onSuccess(list);
        });
    }
}
