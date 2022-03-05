/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.DeptCopyRecord;

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
public class DeptCopy extends TableImpl<DeptCopyRecord> {

    private static final long serialVersionUID = -252520531;

    /**
     * The reference instance of <code>fleet-test.dept_copy</code>
     */
    public static final DeptCopy DEPT_COPY = new DeptCopy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DeptCopyRecord> getRecordType() {
        return DeptCopyRecord.class;
    }

    /**
     * The column <code>fleet-test.dept_copy.id</code>. 部门id
     */
    public final TableField<DeptCopyRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "部门id");

    /**
     * The column <code>fleet-test.dept_copy.name</code>. 部门名称
     */
    public final TableField<DeptCopyRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(64), this, "部门名称");

    /**
     * The column <code>fleet-test.dept_copy.state</code>. 部门状态（1：正常， 0：停用）
     */
    public final TableField<DeptCopyRecord, UInteger> STATE = createField("state", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "部门状态（1：正常， 0：停用）");

    /**
     * The column <code>fleet-test.dept_copy.sort</code>. 排序（数字越大，越排前）
     */
    public final TableField<DeptCopyRecord, UInteger> SORT = createField("sort", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "排序（数字越大，越排前）");

    /**
     * The column <code>fleet-test.dept_copy.creator_id</code>. 创建人
     */
    public final TableField<DeptCopyRecord, UInteger> CREATOR_ID = createField("creator_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "创建人");

    /**
     * The column <code>fleet-test.dept_copy.create_time</code>. 创建时间
     */
    public final TableField<DeptCopyRecord, LocalDateTime> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "创建时间");

    /**
     * The column <code>fleet-test.dept_copy.editor_id</code>. 修改人
     */
    public final TableField<DeptCopyRecord, UInteger> EDITOR_ID = createField("editor_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED, this, "修改人");

    /**
     * The column <code>fleet-test.dept_copy.edit_time</code>. 修改时间
     */
    public final TableField<DeptCopyRecord, LocalDateTime> EDIT_TIME = createField("edit_time", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "修改时间");

    /**
     * The column <code>fleet-test.dept_copy.upper_id</code>. 上一级部门id
     */
    public final TableField<DeptCopyRecord, UInteger> UPPER_ID = createField("upper_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "上一级部门id");

    /**
     * The column <code>fleet-test.dept_copy.deleted</code>. 是否删除（1：是，0：否）
     */
    public final TableField<DeptCopyRecord, UInteger> DELETED = createField("deleted", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "是否删除（1：是，0：否）");

    /**
     * Create a <code>fleet-test.dept_copy</code> table reference
     */
    public DeptCopy() {
        this(DSL.name("dept_copy"), null);
    }

    /**
     * Create an aliased <code>fleet-test.dept_copy</code> table reference
     */
    public DeptCopy(String alias) {
        this(DSL.name(alias), DEPT_COPY);
    }

    /**
     * Create an aliased <code>fleet-test.dept_copy</code> table reference
     */
    public DeptCopy(Name alias) {
        this(alias, DEPT_COPY);
    }

    private DeptCopy(Name alias, Table<DeptCopyRecord> aliased) {
        this(alias, aliased, null);
    }

    private DeptCopy(Name alias, Table<DeptCopyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("部门管理"));
    }

    public <O extends Record> DeptCopy(Table<O> child, ForeignKey<O, DeptCopyRecord> key) {
        super(child, key, DEPT_COPY);
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
        return Arrays.<Index>asList(Indexes.DEPT_COPY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DeptCopyRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_DEPT_COPY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DeptCopyRecord> getPrimaryKey() {
        return Keys.KEY_DEPT_COPY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DeptCopyRecord>> getKeys() {
        return Arrays.<UniqueKey<DeptCopyRecord>>asList(Keys.KEY_DEPT_COPY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptCopy as(String alias) {
        return new DeptCopy(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptCopy as(Name alias) {
        return new DeptCopy(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DeptCopy rename(String name) {
        return new DeptCopy(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DeptCopy rename(Name name) {
        return new DeptCopy(name, null);
    }
}
