/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.PdtItem;
import com.fleet.jooq.generator.tables.records.PdtItemRecord;

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
public class PdtItemDao extends DAOImpl<PdtItemRecord, com.fleet.jooq.generator.tables.pojos.PdtItem, Integer> {

    /**
     * Create a new PdtItemDao without any configuration
     */
    public PdtItemDao() {
        super(PdtItem.PDT_ITEM, com.fleet.jooq.generator.tables.pojos.PdtItem.class);
    }

    /**
     * Create a new PdtItemDao with an attached configuration
     */
    public PdtItemDao(Configuration configuration) {
        super(PdtItem.PDT_ITEM, com.fleet.jooq.generator.tables.pojos.PdtItem.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.fleet.jooq.generator.tables.pojos.PdtItem object) {
        return object.getPdtItemId();
    }

    /**
     * Fetch records that have <code>pdt_item_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.PdtItem> fetchByPdtItemId(Integer... values) {
        return fetch(PdtItem.PDT_ITEM.PDT_ITEM_ID, values);
    }

    /**
     * Fetch a unique record that has <code>pdt_item_id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.PdtItem fetchOneByPdtItemId(Integer value) {
        return fetchOne(PdtItem.PDT_ITEM.PDT_ITEM_ID, value);
    }

    /**
     * Fetch records that have <code>index IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.PdtItem> fetchByIndex(Integer... values) {
        return fetch(PdtItem.PDT_ITEM.INDEX, values);
    }

    /**
     * Fetch records that have <code>role IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.PdtItem> fetchByRole(String... values) {
        return fetch(PdtItem.PDT_ITEM.ROLE, values);
    }
}