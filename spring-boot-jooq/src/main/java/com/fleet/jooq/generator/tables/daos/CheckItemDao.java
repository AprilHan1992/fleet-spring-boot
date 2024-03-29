/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.CheckItem;
import com.fleet.jooq.generator.tables.records.CheckItemRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class CheckItemDao extends DAOImpl<CheckItemRecord, com.fleet.jooq.generator.tables.pojos.CheckItem, Integer> {

    /**
     * Create a new CheckItemDao without any configuration
     */
    public CheckItemDao() {
        super(CheckItem.CHECK_ITEM, com.fleet.jooq.generator.tables.pojos.CheckItem.class);
    }

    /**
     * Create a new CheckItemDao with an attached configuration
     */
    public CheckItemDao(Configuration configuration) {
        super(CheckItem.CHECK_ITEM, com.fleet.jooq.generator.tables.pojos.CheckItem.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.fleet.jooq.generator.tables.pojos.CheckItem object) {
        return object.getCheckItemId();
    }

    /**
     * Fetch records that have <code>check_item_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByCheckItemId(Integer... values) {
        return fetch(CheckItem.CHECK_ITEM.CHECK_ITEM_ID, values);
    }

    /**
     * Fetch a unique record that has <code>check_item_id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.CheckItem fetchOneByCheckItemId(Integer value) {
        return fetchOne(CheckItem.CHECK_ITEM.CHECK_ITEM_ID, value);
    }

    /**
     * Fetch records that have <code>index IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByIndex(Integer... values) {
        return fetch(CheckItem.CHECK_ITEM.INDEX, values);
    }

    /**
     * Fetch records that have <code>check_type IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByCheckType(String... values) {
        return fetch(CheckItem.CHECK_ITEM.CHECK_TYPE, values);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByType(String... values) {
        return fetch(CheckItem.CHECK_ITEM.TYPE, values);
    }

    /**
     * Fetch records that have <code>check_item IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByCheckItem(String... values) {
        return fetch(CheckItem.CHECK_ITEM.CHECK_ITEM_, values);
    }

    /**
     * Fetch records that have <code>ratio IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.CheckItem> fetchByRatio(Integer... values) {
        return fetch(CheckItem.CHECK_ITEM.RATIO, values);
    }
}
