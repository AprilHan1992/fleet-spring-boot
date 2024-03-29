/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.DictRecord;

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
public class Dict extends TableImpl<DictRecord> {

    private static final long serialVersionUID = 1889012005;

    /**
     * The reference instance of <code>fleet-test.dict</code>
     */
    public static final Dict DICT = new Dict();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DictRecord> getRecordType() {
        return DictRecord.class;
    }

    /**
     * The column <code>fleet-test.dict.id</code>. 字典id
     */
    public final TableField<DictRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "字典id");

    /**
     * The column <code>fleet-test.dict.group</code>. 字典名称
     */
    public final TableField<DictRecord, String> GROUP = createField("group", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "字典名称");

    /**
     * The column <code>fleet-test.dict.remark</code>. 字典描述
     */
    public final TableField<DictRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.VARCHAR(255), this, "字典描述");

    /**
     * The column <code>fleet-test.dict.deleted</code>. 是否删除（1：是，0：否）
     */
    public final TableField<DictRecord, UInteger> DELETED = createField("deleted", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGERUNSIGNED)), this, "是否删除（1：是，0：否）");

    /**
     * Create a <code>fleet-test.dict</code> table reference
     */
    public Dict() {
        this(DSL.name("dict"), null);
    }

    /**
     * Create an aliased <code>fleet-test.dict</code> table reference
     */
    public Dict(String alias) {
        this(DSL.name(alias), DICT);
    }

    /**
     * Create an aliased <code>fleet-test.dict</code> table reference
     */
    public Dict(Name alias) {
        this(alias, DICT);
    }

    private Dict(Name alias, Table<DictRecord> aliased) {
        this(alias, aliased, null);
    }

    private Dict(Name alias, Table<DictRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Dict(Table<O> child, ForeignKey<O, DictRecord> key) {
        super(child, key, DICT);
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
        return Arrays.<Index>asList(Indexes.DICT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DictRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_DICT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DictRecord> getPrimaryKey() {
        return Keys.KEY_DICT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DictRecord>> getKeys() {
        return Arrays.<UniqueKey<DictRecord>>asList(Keys.KEY_DICT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dict as(String alias) {
        return new Dict(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dict as(Name alias) {
        return new Dict(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Dict rename(String name) {
        return new Dict(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Dict rename(Name name) {
        return new Dict(name, null);
    }
}
