/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.CheckItemRecord;

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
public class CheckItem extends TableImpl<CheckItemRecord> {

    private static final long serialVersionUID = 264982489;

    /**
     * The reference instance of <code>fleet-test.check_item</code>
     */
    public static final CheckItem CHECK_ITEM = new CheckItem();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CheckItemRecord> getRecordType() {
        return CheckItemRecord.class;
    }

    /**
     * The column <code>fleet-test.check_item.check_item_id</code>.
     */
    public final TableField<CheckItemRecord, Integer> CHECK_ITEM_ID = createField("check_item_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>fleet-test.check_item.index</code>.
     */
    public final TableField<CheckItemRecord, Integer> INDEX = createField("index", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>fleet-test.check_item.check_type</code>.
     */
    public final TableField<CheckItemRecord, String> CHECK_TYPE = createField("check_type", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>fleet-test.check_item.type</code>.
     */
    public final TableField<CheckItemRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>fleet-test.check_item.check_item</code>.
     */
    public final TableField<CheckItemRecord, String> CHECK_ITEM_ = createField("check_item", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>fleet-test.check_item.ratio</code>.
     */
    public final TableField<CheckItemRecord, Integer> RATIO = createField("ratio", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>fleet-test.check_item</code> table reference
     */
    public CheckItem() {
        this(DSL.name("check_item"), null);
    }

    /**
     * Create an aliased <code>fleet-test.check_item</code> table reference
     */
    public CheckItem(String alias) {
        this(DSL.name(alias), CHECK_ITEM);
    }

    /**
     * Create an aliased <code>fleet-test.check_item</code> table reference
     */
    public CheckItem(Name alias) {
        this(alias, CHECK_ITEM);
    }

    private CheckItem(Name alias, Table<CheckItemRecord> aliased) {
        this(alias, aliased, null);
    }

    private CheckItem(Name alias, Table<CheckItemRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> CheckItem(Table<O> child, ForeignKey<O, CheckItemRecord> key) {
        super(child, key, CHECK_ITEM);
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
        return Arrays.<Index>asList(Indexes.CHECK_ITEM_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CheckItemRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CHECK_ITEM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CheckItemRecord> getPrimaryKey() {
        return Keys.KEY_CHECK_ITEM_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CheckItemRecord>> getKeys() {
        return Arrays.<UniqueKey<CheckItemRecord>>asList(Keys.KEY_CHECK_ITEM_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckItem as(String alias) {
        return new CheckItem(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckItem as(Name alias) {
        return new CheckItem(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CheckItem rename(String name) {
        return new CheckItem(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CheckItem rename(Name name) {
        return new CheckItem(name, null);
    }
}
