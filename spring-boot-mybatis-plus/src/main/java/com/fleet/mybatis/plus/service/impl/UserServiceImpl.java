package com.fleet.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleet.mybatis.plus.dao.UserDao;
import com.fleet.mybatis.plus.entity.User;
import com.fleet.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public Boolean deleteByName(String name) {
        if (userDao.deleteByName(name) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Page<User> listPage(Page<User> page, Map<String, Object> map) {
        page.setRecords(baseMapper.list(page, map));
        return page;
    }
}
