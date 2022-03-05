package com.fleet.mybatis.generator.dao;

import com.fleet.mybatis.generator.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}