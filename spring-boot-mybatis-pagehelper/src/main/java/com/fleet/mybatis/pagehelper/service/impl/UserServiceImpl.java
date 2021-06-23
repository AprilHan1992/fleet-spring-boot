package com.fleet.mybatis.pagehelper.service.impl;

import com.fleet.mybatis.pagehelper.dao.UserDao;
import com.fleet.mybatis.pagehelper.entity.User;
import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.fleet.mybatis.pagehelper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean insert(User user) {
        if (userDao.insert(user) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(User user) {
        if (userDao.delete(user) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(User user) {
        if (userDao.update(user) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public User get(User user) {
        return userDao.get(user);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userDao.list(map);
    }

    @Override
    public PageUtil<User> listPage(Map<String, Object> map, Page page) {
        PageUtil<User> pageUtil = new PageUtil<>();
        List<User> list = userDao.list(map);
        pageUtil.setList(list);
        return pageUtil;
    }
}
