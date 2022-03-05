/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.Money;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


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
public class MoneyRecord extends TableRecordImpl<MoneyRecord> implements Record2<String, Integer> {

    private static final long serialVersionUID = -40148241;

    /**
     * Setter for <code>fleet-test.money.name</code>.
     */
    public void setName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.money.name</code>.
     */
    public String getName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>fleet-test.money.money</code>.
     */
    public void setMoney(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.money.money</code>.
     */
    public Integer getMoney() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Money.MONEY.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Money.MONEY.MONEY_;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getMoney();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getMoney();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyRecord value1(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyRecord value2(Integer value) {
        setMoney(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoneyRecord values(String value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MoneyRecord
     */
    public MoneyRecord() {
        super(Money.MONEY);
    }

    /**
     * Create a detached, initialised MoneyRecord
     */
    public MoneyRecord(String name, Integer money) {
        super(Money.MONEY);

        set(0, name);
        set(1, money);
    }
}