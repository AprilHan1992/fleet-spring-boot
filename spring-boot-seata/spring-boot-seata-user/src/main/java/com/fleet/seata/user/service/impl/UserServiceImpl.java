package com.fleet.seata.user.service.impl;

import com.fleet.seata.user.dao.UserDao;
import com.fleet.seata.user.entity.User;
import com.fleet.seata.user.service.UserService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public boolean pay(Integer id, Integer money) {
        String xid = RootContext.getXID();
        logger.info("user 服务：" + xid);

        if (userDao.pay(id, money) == 0) {
            return false;
        }
        return true;
    }
}
