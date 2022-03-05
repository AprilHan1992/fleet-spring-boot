/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.UserRole;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;
import org.jooq.types.UInteger;


/**
 * 用户与角色对应信息
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRoleRecord extends TableRecordImpl<UserRoleRecord> implements Record2<UInteger, UInteger> {

    private static final long serialVersionUID = -1582949722;

    /**
     * Setter for <code>fleet-test.user_role.user_id</code>. 用户id
     */
    public void setUserId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.user_role.user_id</code>. 用户id
     */
    public UInteger getUserId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.user_role.role_id</code>. 角色id
     */
    public void setRoleId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.user_role.role_id</code>. 角色id
     */
    public UInteger getRoleId() {
        return (UInteger) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, UInteger> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<UInteger, UInteger> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return UserRole.USER_ROLE.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return UserRole.USER_ROLE.ROLE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getRoleId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRoleRecord value1(UInteger value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRoleRecord value2(UInteger value) {
        setRoleId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRoleRecord values(UInteger value1, UInteger value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRoleRecord
     */
    public UserRoleRecord() {
        super(UserRole.USER_ROLE);
    }

    /**
     * Create a detached, initialised UserRoleRecord
     */
    public UserRoleRecord(UInteger userId, UInteger roleId) {
        super(UserRole.USER_ROLE);

        set(0, userId);
        set(1, roleId);
    }
}