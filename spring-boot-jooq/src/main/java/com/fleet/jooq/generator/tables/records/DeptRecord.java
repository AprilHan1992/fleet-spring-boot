/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.Dept;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * 部门管理
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DeptRecord extends UpdatableRecordImpl<DeptRecord> implements Record10<UInteger, String, UInteger, UInteger, UInteger, LocalDateTime, UInteger, LocalDateTime, UInteger, UInteger> {

    private static final long serialVersionUID = 2087577026;

    /**
     * Setter for <code>fleet-test.dept.id</code>. 部门id
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.dept.id</code>. 部门id
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.dept.name</code>. 部门名称
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.dept.name</code>. 部门名称
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>fleet-test.dept.state</code>. 部门状态（1：正常， 0：停用）
     */
    public void setState(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.dept.state</code>. 部门状态（1：正常， 0：停用）
     */
    public UInteger getState() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>fleet-test.dept.sort</code>. 排序（数字越大，越排前）
     */
    public void setSort(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>fleet-test.dept.sort</code>. 排序（数字越大，越排前）
     */
    public UInteger getSort() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>fleet-test.dept.creator_id</code>. 创建人
     */
    public void setCreatorId(UInteger value) {
        set(4, value);
    }

    /**
     * Getter for <code>fleet-test.dept.creator_id</code>. 创建人
     */
    public UInteger getCreatorId() {
        return (UInteger) get(4);
    }

    /**
     * Setter for <code>fleet-test.dept.create_time</code>. 创建时间
     */
    public void setCreateTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>fleet-test.dept.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>fleet-test.dept.editor_id</code>. 修改人
     */
    public void setEditorId(UInteger value) {
        set(6, value);
    }

    /**
     * Getter for <code>fleet-test.dept.editor_id</code>. 修改人
     */
    public UInteger getEditorId() {
        return (UInteger) get(6);
    }

    /**
     * Setter for <code>fleet-test.dept.edit_time</code>. 修改时间
     */
    public void setEditTime(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>fleet-test.dept.edit_time</code>. 修改时间
     */
    public LocalDateTime getEditTime() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>fleet-test.dept.upper_id</code>. 上一级部门id
     */
    public void setUpperId(UInteger value) {
        set(8, value);
    }

    /**
     * Getter for <code>fleet-test.dept.upper_id</code>. 上一级部门id
     */
    public UInteger getUpperId() {
        return (UInteger) get(8);
    }

    /**
     * Setter for <code>fleet-test.dept.deleted</code>. 是否删除（1：是，0：否）
     */
    public void setDeleted(UInteger value) {
        set(9, value);
    }

    /**
     * Getter for <code>fleet-test.dept.deleted</code>. 是否删除（1：是，0：否）
     */
    public UInteger getDeleted() {
        return (UInteger) get(9);
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
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<UInteger, String, UInteger, UInteger, UInteger, LocalDateTime, UInteger, LocalDateTime, UInteger, UInteger> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<UInteger, String, UInteger, UInteger, UInteger, LocalDateTime, UInteger, LocalDateTime, UInteger, UInteger> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Dept.DEPT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Dept.DEPT.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return Dept.DEPT.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return Dept.DEPT.SORT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field5() {
        return Dept.DEPT.CREATOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return Dept.DEPT.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field7() {
        return Dept.DEPT.EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field8() {
        return Dept.DEPT.EDIT_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field9() {
        return Dept.DEPT.UPPER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field10() {
        return Dept.DEPT.DELETED;
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
    public UInteger component3() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component4() {
        return getSort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component5() {
        return getCreatorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component6() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component7() {
        return getEditorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component8() {
        return getEditTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component9() {
        return getUpperId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component10() {
        return getDeleted();
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
    public UInteger value3() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getSort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value5() {
        return getCreatorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value7() {
        return getEditorId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value8() {
        return getEditTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value9() {
        return getUpperId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value10() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value3(UInteger value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value4(UInteger value) {
        setSort(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value5(UInteger value) {
        setCreatorId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value6(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value7(UInteger value) {
        setEditorId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value8(LocalDateTime value) {
        setEditTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value9(UInteger value) {
        setUpperId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord value10(UInteger value) {
        setDeleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptRecord values(UInteger value1, String value2, UInteger value3, UInteger value4, UInteger value5, LocalDateTime value6, UInteger value7, LocalDateTime value8, UInteger value9, UInteger value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DeptRecord
     */
    public DeptRecord() {
        super(Dept.DEPT);
    }

    /**
     * Create a detached, initialised DeptRecord
     */
    public DeptRecord(UInteger id, String name, UInteger state, UInteger sort, UInteger creatorId, LocalDateTime createTime, UInteger editorId, LocalDateTime editTime, UInteger upperId, UInteger deleted) {
        super(Dept.DEPT);

        set(0, id);
        set(1, name);
        set(2, state);
        set(3, sort);
        set(4, creatorId);
        set(5, createTime);
        set(6, editorId);
        set(7, editTime);
        set(8, upperId);
        set(9, deleted);
    }
}
