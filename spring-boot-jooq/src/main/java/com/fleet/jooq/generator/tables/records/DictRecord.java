/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.Dict;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class DictRecord extends UpdatableRecordImpl<DictRecord> implements Record4<UInteger, String, String, UInteger> {

    private static final long serialVersionUID = -184306449;

    /**
     * Setter for <code>fleet-test.dict.id</code>. 字典id
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.dict.id</code>. 字典id
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.dict.group</code>. 字典名称
     */
    public void setGroup(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.dict.group</code>. 字典名称
     */
    public String getGroup() {
        return (String) get(1);
    }

    /**
     * Setter for <code>fleet-test.dict.remark</code>. 字典描述
     */
    public void setRemark(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.dict.remark</code>. 字典描述
     */
    public String getRemark() {
        return (String) get(2);
    }

    /**
     * Setter for <code>fleet-test.dict.deleted</code>. 是否删除（1：是，0：否）
     */
    public void setDeleted(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>fleet-test.dict.deleted</code>. 是否删除（1：是，0：否）
     */
    public UInteger getDeleted() {
        return (UInteger) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, String, UInteger> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<UInteger, String, String, UInteger> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Dict.DICT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Dict.DICT.GROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Dict.DICT.REMARK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return Dict.DICT.DELETED;
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
        return getGroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component4() {
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
        return getGroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DictRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DictRecord value2(String value) {
        setGroup(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DictRecord value3(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DictRecord value4(UInteger value) {
        setDeleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DictRecord values(UInteger value1, String value2, String value3, UInteger value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DictRecord
     */
    public DictRecord() {
        super(Dict.DICT);
    }

    /**
     * Create a detached, initialised DictRecord
     */
    public DictRecord(UInteger id, String group, String remark, UInteger deleted) {
        super(Dict.DICT);

        set(0, id);
        set(1, group);
        set(2, remark);
        set(3, deleted);
    }
}
