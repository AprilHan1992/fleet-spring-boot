package com.fleet.mysql.multi.service.impl;

import com.fleet.mysql.multi.dao.master.MasterUserDao;
import com.fleet.mysql.multi.dao.slaver.SlaverUserDao;
import com.fleet.mysql.multi.entity.User;
import com.fleet.mysql.multi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MasterUserDao masterUserDao;

    @Resource
    private SlaverUserDao slaverUserDao;

    @Override
    public int insert(User user) {
        return masterUserDao.insert(user);
    }

    @Override
    public int delete(Long id) {
        return masterUserDao.delete(id);
    }

    @Override
    public int update(User user) {
        return masterUserDao.update(user);
    }

    @Override
    public User get(Long id) {
        return slaverUserDao.get(id);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return slaverUserDao.list(map);
    }
}
