package com.fleet.mybatis.dynamic.sql.dao;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {
    public static final UserRole userRole = new UserRole();

    /**
     * 用户id
     */
    public static final SqlColumn<Integer> userId = userRole.userId;

    /**
     * 角色id
     */
    public static final SqlColumn<Integer> roleId = userRole.roleId;

    public static final class UserRole extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> roleId = column("role_id", JDBCType.INTEGER);

        public UserRole() {
            super("user_role");
        }
    }
}