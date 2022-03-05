package com.fleet.jooq.service.impl;

import com.fleet.jooq.generator.tables.pojos.User;
import com.fleet.jooq.service.UserService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.types.UInteger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    DSLContext dsl;

    com.fleet.jooq.generator.tables.User u = com.fleet.jooq.generator.tables.User.USER;

    @Override
    public void insert(User user) {
        dsl.insertInto(u).
                columns(u.ID, u.NAME).
                values(user.getId(), user.getName())
                .execute();
    }

    @Override
    public void delete(Integer id) {
        dsl.delete(u).where(u.ID.eq(UInteger.valueOf(id))).execute();
    }

    @Override
    public void update(User user) {
        dsl.update(u).set((Record) user);
    }

    @Override
    public User get(Integer id) {
        List<User> list = dsl.select()
                .from(u)
                .where(u.ID.eq(UInteger.valueOf(id)))
                .fetch(r -> r.into(User.class));
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<User> list() {
        return dsl.select().from(u).fetch(r -> r.into(User.class));
    }
}
