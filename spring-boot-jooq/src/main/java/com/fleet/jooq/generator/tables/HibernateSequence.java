/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.tables.records.HibernateSequenceRecord;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class HibernateSequence extends TableImpl<HibernateSequenceRecord> {

    private static final long serialVersionUID = -1874007073;

    /**
     * The reference instance of <code>fleet-test.hibernate_sequence</code>
     */
    public static final HibernateSequence HIBERNATE_SEQUENCE = new HibernateSequence();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HibernateSequenceRecord> getRecordType() {
        return HibernateSequenceRecord.class;
    }

    /**
     * The column <code>fleet-test.hibernate_sequence.next_val</code>.
     */
    public final TableField<HibernateSequenceRecord, Long> NEXT_VAL = createField("next_val", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>fleet-test.hibernate_sequence</code> table reference
     */
    public HibernateSequence() {
        this(DSL.name("hibernate_sequence"), null);
    }

    /**
     * Create an aliased <code>fleet-test.hibernate_sequence</code> table reference
     */
    public HibernateSequence(String alias) {
        this(DSL.name(alias), HIBERNATE_SEQUENCE);
    }

    /**
     * Create an aliased <code>fleet-test.hibernate_sequence</code> table reference
     */
    public HibernateSequence(Name alias) {
        this(alias, HIBERNATE_SEQUENCE);
    }

    private HibernateSequence(Name alias, Table<HibernateSequenceRecord> aliased) {
        this(alias, aliased, null);
    }

    private HibernateSequence(Name alias, Table<HibernateSequenceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> HibernateSequence(Table<O> child, ForeignKey<O, HibernateSequenceRecord> key) {
        super(child, key, HIBERNATE_SEQUENCE);
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
    public HibernateSequence as(String alias) {
        return new HibernateSequence(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HibernateSequence as(Name alias) {
        return new HibernateSequence(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public HibernateSequence rename(String name) {
        return new HibernateSequence(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public HibernateSequence rename(Name name) {
        return new HibernateSequence(name, null);
    }
}