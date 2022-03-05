package com.fleet.mybatis.dynamic.sql.dao;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    public static final User user = new User();

    /**
     * 用户id
     */
    public static final SqlColumn<Integer> id = user.id;

    /**
     * 用户名
     */
    public static final SqlColumn<String> name = user.name;

    public static final class User extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public User() {
            super("user");
        }
    }
}