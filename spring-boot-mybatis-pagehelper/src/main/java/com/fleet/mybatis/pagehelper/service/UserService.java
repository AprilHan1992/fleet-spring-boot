package com.fleet.mybatis.pagehelper.service;

import com.fleet.mybatis.pagehelper.entity.User;
import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.page.PagerUtil;
import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.fleet.mybatis.pagehelper.page.entity.Pager;

import java.util.List;
import java.util.Map;

public interface UserService {

    Boolean insert(User user);

    Boolean delete(User user);

    Boolean update(User user);

    User get(User user);

    List<User> list(Map<String, Object> map);

    PageUtil<User> listPage(Page page);

    PagerUtil<User> listPager(Map<String, Object> map, Pager pager);
}
