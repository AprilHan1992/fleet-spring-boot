/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.UserCopy1Record;

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
public class UserCopy1 extends TableImpl<UserCopy1Record> {

    private static final long serialVersionUID = 962427223;

    /**
     * The reference instance of <code>fleet-test.user_copy1</code>
     */
    public static final UserCopy1 USER_COPY1 = new UserCopy1();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserCopy1Record> getRecordType() {
        return UserCopy1Record.class;
    }

    /**
     * The column <code>fleet-test.user_copy1.id</code>. 用户id
     */
    public final TableField<UserCopy1Record, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "用户id");

    /**
     * The column <code>fleet-test.user_copy1.name</code>. 用户名（登录使用）
     */
    public final TableField<UserCopy1Record, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "用户名（登录使用）");

    /**
     * Create a <code>fleet-test.user_copy1</code> table reference
     */
    public UserCopy1() {
        this(DSL.name("user_copy1"), null);
    }

    /**
     * Create an aliased <code>fleet-test.user_copy1</code> table reference
     */
    public UserCopy1(String alias) {
        this(DSL.name(alias), USER_COPY1);
    }

    /**
     * Create an aliased <code>fleet-test.user_copy1</code> table reference
     */
    public UserCopy1(Name alias) {
        this(alias, USER_COPY1);
    }

    private UserCopy1(Name alias, Table<UserCopy1Record> aliased) {
        this(alias, aliased, null);
    }

    private UserCopy1(Name alias, Table<UserCopy1Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserCopy1(Table<O> child, ForeignKey<O, UserCopy1Record> key) {
        super(child, key, USER_COPY1);
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
        return Arrays.<Index>asList(Indexes.USER_COPY1_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserCopy1Record, UInteger> getIdentity() {
        return Keys.IDENTITY_USER_COPY1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserCopy1Record> getPrimaryKey() {
        return Keys.KEY_USER_COPY1_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserCopy1Record>> getKeys() {
        return Arrays.<UniqueKey<UserCopy1Record>>asList(Keys.KEY_USER_COPY1_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopy1 as(String alias) {
        return new UserCopy1(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopy1 as(Name alias) {
        return new UserCopy1(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserCopy1 rename(String name) {
        return new UserCopy1(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserCopy1 rename(Name name) {
        return new UserCopy1(name, null);
    }
}
