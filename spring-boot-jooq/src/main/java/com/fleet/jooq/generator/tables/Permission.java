/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.PermissionRecord;

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
 * 权限管理
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Permission extends TableImpl<PermissionRecord> {

    private static final long serialVersionUID = 806881998;

    /**
     * The reference instance of <code>fleet-test.permission</code>
     */
    public static final Permission PERMISSION = new Permission();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PermissionRecord> getRecordType() {
        return PermissionRecord.class;
    }

    /**
     * The column <code>fleet-test.permission.permission_id</code>. 权限标识
     */
    public final TableField<PermissionRecord, UInteger> PERMISSION_ID = createField("permission_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "权限标识");

    /**
     * The column <code>fleet-test.permission.permission_name</code>. 权限名称
     */
    public final TableField<PermissionRecord, String> PERMISSION_NAME = createField("permission_name", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "权限名称");

    /**
     * The column <code>fleet-test.permission.permission_code</code>. 权限代码（如果权限项是包容关系，使用层级关系）
     */
    public final TableField<PermissionRecord, String> PERMISSION_CODE = createField("permission_code", org.jooq.impl.SQLDataType.VARCHAR(128), this, "权限代码（如果权限项是包容关系，使用层级关系）");

    /**
     * The column <code>fleet-test.permission.permission_order</code>. 排序（数字越大，越排前）
     */
    public final TableField<PermissionRecord, UInteger> PERMISSION_ORDER = createField("permission_order", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "排序（数字越大，越排前）");

    /**
     * The column <code>fleet-test.permission.upper_permission_id</code>. 上一级权限
     */
    public final TableField<PermissionRecord, UInteger> UPPER_PERMISSION_ID = createField("upper_permission_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "上一级权限");

    /**
     * Create a <code>fleet-test.permission</code> table reference
     */
    public Permission() {
        this(DSL.name("permission"), null);
    }

    /**
     * Create an aliased <code>fleet-test.permission</code> table reference
     */
    public Permission(String alias) {
        this(DSL.name(alias), PERMISSION);
    }

    /**
     * Create an aliased <code>fleet-test.permission</code> table reference
     */
    public Permission(Name alias) {
        this(alias, PERMISSION);
    }

    private Permission(Name alias, Table<PermissionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Permission(Name alias, Table<PermissionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("权限管理"));
    }

    public <O extends Record> Permission(Table<O> child, ForeignKey<O, PermissionRecord> key) {
        super(child, key, PERMISSION);
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
        return Arrays.<Index>asList(Indexes.PERMISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PermissionRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_PERMISSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PermissionRecord> getPrimaryKey() {
        return Keys.KEY_PERMISSION_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PermissionRecord>> getKeys() {
        return Arrays.<UniqueKey<PermissionRecord>>asList(Keys.KEY_PERMISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Permission as(String alias) {
        return new Permission(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Permission as(Name alias) {
        return new Permission(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Permission rename(String name) {
        return new Permission(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Permission rename(Name name) {
        return new Permission(name, null);
    }
}
