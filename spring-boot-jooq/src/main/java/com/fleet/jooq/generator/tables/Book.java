/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables;


import com.fleet.jooq.generator.FleetTest;
import com.fleet.jooq.generator.Indexes;
import com.fleet.jooq.generator.Keys;
import com.fleet.jooq.generator.tables.records.BookRecord;

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
public class Book extends TableImpl<BookRecord> {

    private static final long serialVersionUID = -1853478100;

    /**
     * The reference instance of <code>fleet-test.book</code>
     */
    public static final Book BOOK = new Book();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookRecord> getRecordType() {
        return BookRecord.class;
    }

    /**
     * The column <code>fleet-test.book.id</code>.
     */
    public final TableField<BookRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>fleet-test.book.title</code>.
     */
    public final TableField<BookRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>fleet-test.book.author_id</code>.
     */
    public final TableField<BookRecord, Integer> AUTHOR_ID = createField("author_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>fleet-test.book</code> table reference
     */
    public Book() {
        this(DSL.name("book"), null);
    }

    /**
     * Create an aliased <code>fleet-test.book</code> table reference
     */
    public Book(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>fleet-test.book</code> table reference
     */
    public Book(Name alias) {
        this(alias, BOOK);
    }

    private Book(Name alias, Table<BookRecord> aliased) {
        this(alias, aliased, null);
    }

    private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Book(Table<O> child, ForeignKey<O, BookRecord> key) {
        super(child, key, BOOK);
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
        return Arrays.<Index>asList(Indexes.BOOK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<BookRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_BOOK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BookRecord> getPrimaryKey() {
        return Keys.KEY_BOOK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BookRecord>> getKeys() {
        return Arrays.<UniqueKey<BookRecord>>asList(Keys.KEY_BOOK_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book as(String alias) {
        return new Book(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book as(Name alias) {
        return new Book(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(String name) {
        return new Book(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Name name) {
        return new Book(name, null);
    }
}
