/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.UserCopy1;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;
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
public class UserCopy1Record extends UpdatableRecordImpl<UserCopy1Record> implements Record2<UInteger, String> {

    private static final long serialVersionUID = -1304697873;

    /**
     * Setter for <code>fleet-test.user_copy1.id</code>. 用户id
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.user_copy1.id</code>. 用户id
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.user_copy1.name</code>. 用户名（登录使用）
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.user_copy1.name</code>. 用户名（登录使用）
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return UserCopy1.USER_COPY1.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UserCopy1.USER_COPY1.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopy1Record value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopy1Record value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserCopy1Record values(UInteger value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserCopy1Record
     */
    public UserCopy1Record() {
        super(UserCopy1.USER_COPY1);
    }

    /**
     * Create a detached, initialised UserCopy1Record
     */
    public UserCopy1Record(UInteger id, String name) {
        super(UserCopy1.USER_COPY1);

        set(0, id);
        set(1, name);
    }
}