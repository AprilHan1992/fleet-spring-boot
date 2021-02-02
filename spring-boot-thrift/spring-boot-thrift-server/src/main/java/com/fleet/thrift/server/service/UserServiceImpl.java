package com.fleet.thrift.server.service;

import com.fleet.thrift.common.gen.DataException;
import com.fleet.thrift.common.gen.User;
import com.fleet.thrift.common.gen.UserService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService.Iface {

    @Override
    public int insert(User user) throws DataException, TException {
        System.out.println("保存成功");
        return 0;
    }

    @Override
    public User get(int id) throws DataException, TException {
        User user = new User();
        user.setId(id);
        user.setName("fleet");
        user.setAge(18);
        return user;
    }
}
