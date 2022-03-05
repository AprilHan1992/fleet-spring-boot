/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.UserCopyCopyRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserCopyCopy extends TableImpl<UserCopyCopyRecord> {

    private static final long serialVersionUID = 391165780;

    /**
     * The reference instance of <code>fleet-test.user_copy_copy</code>
     */
    public static final UserCopyCopy USER_COPY_COPY = new UserCopyCopy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserCopyCopyRecord> getRecordType() {
        return UserCopyCopyRecord.class;
    }

    /**
     * The column <code>fleet-test.user_copy_copy.id</code>. 用户id
     */
    public final TableField<UserCopyCopyRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "用户id");

    /**
     * The column <code>fleet-test.user_copy_copy.name</code>. 用户名（登录使用）
     */
    public final TableField<UserCopyCopyRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "用户名（登录使用）");

    /**
     * The column <code>fleet-test.user_copy_copy.nick_name</code>. 昵称（不做登录使用）
     */
    public final TableField<UserCopyCopyRecord, String> NICK_NAME = createField("nick_name", org.jooq.impl.SQLDataType.VARCHAR(64), this, "昵称（不做登录使用）");

    /**
     * The column <code>fleet-test.user_copy_copy.email</code>. 邮箱
     */
    public final TableField<UserCopyCopyRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR(64), this, "邮箱");

    /**
     * The column <code>fleet-test.user_copy_copy.pwd</code>. 密码
     */
    public final TableField<UserCopyCopyRecord, String> PWD = createField("pwd", org.jooq.impl.SQLDataType.VARCHAR(64), this, "密码");

    /**
     * The column <code>fleet-test.user_copy_copy.pwd_salt</code>. 密码加盐
     */
    public final TableField<UserCopyCopyRecord, String> PWD_SALT = createField("pwd_salt", org.jooq.impl.SQLDataType.VARCHAR(32), this, "密码加盐");

    /**
     * The column <code>fleet-test.user_copy_copy.state</code>. 用户状态（0：禁用，1：正常，2：锁定）
     */
    public final TableField<UserCopyCopyRecord, UInteger> STATE = createField("state", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "用户状态（0：禁用，1：正常，2：锁定）");

    /**
     * The column <code>fleet-test.user_copy_copy.reg_time</code>. 注册时间
     */
    public final TableField<UserCopyCopyRecord, LocalDateTime> REG_TIME = createField("reg_time", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "注册时间");

    /**
     * The column <code>fleet-test.user_copy_copy.deleted</code>. 是否删除（1：是，0：否）
     */
    public final TableField<UserCopyCopyRecord, UInteger> DELETED = createField("deleted", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "是否删除（1：是，0：否）");

    /**
     * Create a <code>fleet-test.user_copy_copy</code> table reference
     */
    public UserCopyCopy() {
        this(DSL.name("user_copy_copy"), null);
    }

    /**
     * Create an aliased <code>fleet-test.user_copy_copy</code> table reference
     */
    public UserCopyCopy(String alias) {
        this(DSL.name(alias), USER_COPY_COPY);
    }

    /**
     * Create an aliased <code>fleet-test.user_copy_copy</code> table reference
     */
    public UserCopyCopy(Name alias) {
        this(alias, USER_COPY_COPY);
    }

    private UserCopyCopy(Name alias, Table<UserCopyCopyRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserCopyCopy(Name alias, Table<UserCopyCopyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserCopyCopy(Table<O> child, ForeignKey<O, UserCopyCopyRecord> key) {
        super(child, key, USER_COPY_COPY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return FleetTest.FLEET_TEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_COPY_COPY_PRIMARY, Indexes.USER_COPY_COPY_UNI_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserCopyCopyRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_USER_COPY_COPY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserCopyCopyRecord> getPrimaryKey() {
        return Keys.KEY_USER_COPY_COPY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserCopyCopyRecord>> getKeys() {
        return Arrays.<UniqueKey<UserCopyCopyRecord>>asList(Keys.KEY_USER_COPY_COPY_PRIMARY, Keys.KEY_USER_COPY_COPY_UNI_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopyCopy as(String alias) {
        return new UserCopyCopy(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopyCopy as(Name alias) {
        return new UserCopyCopy(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserCopyCopy rename(String name) {
        return new UserCopyCopy(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserCopyCopy rename(Name name) {
        return new UserCopyCopy(name, null);
    }
}
