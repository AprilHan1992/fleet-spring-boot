/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.UserDept;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;
import org.jooq.types.UInteger;


/**
 * 用户与角色对应关系
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserDeptRecord extends TableRecordImpl<UserDeptRecord> implements Record3<UInteger, UInteger, UInteger> {

    private static final long serialVersionUID = 916253934;

    /**
     * Setter for <code>fleet-test.user_dept.user_id</code>. 用户id
     */
    public void setUserId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.user_dept.user_id</code>. 用户id
     */
    public UInteger getUserId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.user_dept.dept_id</code>. 部门id
     */
    public void setDeptId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.user_dept.dept_id</code>. 部门id
     */
    public UInteger getDeptId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>fleet-test.user_dept.deleted</code>. 是否删除（1：是，0：否）
     */
    public void setDeleted(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.user_dept.deleted</code>. 是否删除（1：是，0：否）
     */
    public UInteger getDeleted() {
        return (UInteger) get(2);
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<UInteger, UInteger, UInteger> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<UInteger, UInteger, UInteger> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return UserDept.USER_DEPT.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return UserDept.USER_DEPT.DEPT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return UserDept.USER_DEPT.DELETED;
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
        return getDeptId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component3() {
        return getDeleted();
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
        return getDeptId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDeptRecord value1(UInteger value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDeptRecord value2(UInteger value) {
        setDeptId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDeptRecord value3(UInteger value) {
        setDeleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDeptRecord values(UInteger value1, UInteger value2, UInteger value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserDeptRecord
     */
    public UserDeptRecord() {
        super(UserDept.USER_DEPT);
    }

    /**
     * Create a detached, initialised UserDeptRecord
     */
    public UserDeptRecord(UInteger userId, UInteger deptId, UInteger deleted) {
        super(UserDept.USER_DEPT);

        set(0, userId);
        set(1, deptId);
        set(2, deleted);
    }
}