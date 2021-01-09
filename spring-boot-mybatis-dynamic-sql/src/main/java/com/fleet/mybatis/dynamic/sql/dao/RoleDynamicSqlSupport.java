package com.fleet.mybatis.dynamic.sql.dao;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RoleDynamicSqlSupport {
    public static final Role role = new Role();

    /**
     * 角色id
     */
    public static final SqlColumn<Integer> id = role.id;

    /**
     * 角色名称
     */
    public static final SqlColumn<String> name = role.name;

    /**
     * 排序（数字越大，越排前）
     */
    public static final SqlColumn<Integer> sort = role.sort;

    /**
     * 上一级角色id
     */
    public static final SqlColumn<Integer> upperId = role.upperId;

    public static final class Role extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<Integer> upperId = column("upper_id", JDBCType.INTEGER);

        public Role() {
            super("role");
        }
    }
}