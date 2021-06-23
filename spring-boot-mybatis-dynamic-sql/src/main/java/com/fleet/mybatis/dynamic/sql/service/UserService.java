package com.fleet.mybatis.dynamic.sql.service;

import com.fleet.mybatis.dynamic.sql.entity.User;
import com.fleet.mybatis.dynamic.sql.page.PageUtil;
import com.fleet.mybatis.dynamic.sql.page.entity.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    Boolean insert(User user);

    Boolean delete(Integer id);

    Boolean update(User user);

    User get(Integer id);

    List<User> list();

    PageUtil<User> listPage(Map<String, Object> map, Page page);
}
