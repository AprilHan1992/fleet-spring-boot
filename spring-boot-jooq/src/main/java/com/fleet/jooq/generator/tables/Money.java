/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.MoneyRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class Money extends TableImpl<MoneyRecord> {

    private static final long serialVersionUID = 326079461;

    /**
     * The reference instance of <code>fleet-test.money</code>
     */
    public static final Money MONEY = new Money();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MoneyRecord> getRecordType() {
        return MoneyRecord.class;
    }

    /**
     * The column <code>fleet-test.money.name</code>.
     */
    public final TableField<MoneyRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>fleet-test.money.money</code>.
     */
    public final TableField<MoneyRecord, Integer> MONEY_ = createField("money", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>fleet-test.money</code> table reference
     */
    public Money() {
        this(DSL.name("money"), null);
    }

    /**
     * Create an aliased <code>fleet-test.money</code> table reference
     */
    public Money(String alias) {
        this(DSL.name(alias), MONEY);
    }

    /**
     * Create an aliased <code>fleet-test.money</code> table reference
     */
    public Money(Name alias) {
        this(alias, MONEY);
    }

    private Money(Name alias, Table<MoneyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Money(Name alias, Table<MoneyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Money(Table<O> child, ForeignKey<O, MoneyRecord> key) {
        super(child, key, MONEY);
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
        return Arrays.<Index>asList(Indexes.MONEY_UNI_);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MoneyRecord>> getKeys() {
        return Arrays.<UniqueKey<MoneyRecord>>asList(Keys.KEY_MONEY_UNI_);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Money as(String alias) {
        return new Money(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Money as(Name alias) {
        return new Money(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Money rename(String name) {
        return new Money(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Money rename(Name name) {
        return new Money(name, null);
    }
}
