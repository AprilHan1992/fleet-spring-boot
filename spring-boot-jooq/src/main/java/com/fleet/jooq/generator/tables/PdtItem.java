/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.PdtItemRecord;

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
public class PdtItem extends TableImpl<PdtItemRecord> {

    private static final long serialVersionUID = -1283029272;

    /**
     * The reference instance of <code>fleet-test.pdt_item</code>
     */
    public static final PdtItem PDT_ITEM = new PdtItem();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PdtItemRecord> getRecordType() {
        return PdtItemRecord.class;
    }

    /**
     * The column <code>fleet-test.pdt_item.pdt_item_id</code>.
     */
    public final TableField<PdtItemRecord, Integer> PDT_ITEM_ID = createField("pdt_item_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>fleet-test.pdt_item.index</code>.
     */
    public final TableField<PdtItemRecord, Integer> INDEX = createField("index", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>fleet-test.pdt_item.role</code>.
     */
    public final TableField<PdtItemRecord, String> ROLE = createField("role", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>fleet-test.pdt_item</code> table reference
     */
    public PdtItem() {
        this(DSL.name("pdt_item"), null);
    }

    /**
     * Create an aliased <code>fleet-test.pdt_item</code> table reference
     */
    public PdtItem(String alias) {
        this(DSL.name(alias), PDT_ITEM);
    }

    /**
     * Create an aliased <code>fleet-test.pdt_item</code> table reference
     */
    public PdtItem(Name alias) {
        this(alias, PDT_ITEM);
    }

    private PdtItem(Name alias, Table<PdtItemRecord> aliased) {
        this(alias, aliased, null);
    }

    private PdtItem(Name alias, Table<PdtItemRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> PdtItem(Table<O> child, ForeignKey<O, PdtItemRecord> key) {
        super(child, key, PDT_ITEM);
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
        return Arrays.<Index>asList(Indexes.PDT_ITEM_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<PdtItemRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PDT_ITEM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PdtItemRecord> getPrimaryKey() {
        return Keys.KEY_PDT_ITEM_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PdtItemRecord>> getKeys() {
        return Arrays.<UniqueKey<PdtItemRecord>>asList(Keys.KEY_PDT_ITEM_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PdtItem as(String alias) {
        return new PdtItem(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PdtItem as(Name alias) {
        return new PdtItem(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PdtItem rename(String name) {
        return new PdtItem(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PdtItem rename(Name name) {
        return new PdtItem(name, null);
    }
}
