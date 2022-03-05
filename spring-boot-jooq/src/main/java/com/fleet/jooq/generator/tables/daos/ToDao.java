/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.To;
import com.fleet.jooq.generator.tables.records.ToRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * 角色
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ToDao extends DAOImpl<ToRecord, com.fleet.jooq.generator.tables.pojos.To, UInteger> {

    /**
     * Create a new ToDao without any configuration
     */
    public ToDao() {
        super(To.TO, com.fleet.jooq.generator.tables.pojos.To.class);
    }

    /**
     * Create a new ToDao with an attached configuration
     */
    public ToDao(Configuration configuration) {
        super(To.TO, com.fleet.jooq.generator.tables.pojos.To.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.fleet.jooq.generator.tables.pojos.To object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchById(UInteger... values) {
        return fetch(To.TO.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.To fetchOneById(UInteger value) {
        return fetchOne(To.TO.ID, value);
    }

    /**
     * Fetch records that have <code>msg_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchByMsgId(UInteger... values) {
        return fetch(To.TO.MSG_ID, values);
    }

    /**
     * Fetch records that have <code>to IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchByTo(UInteger... values) {
        return fetch(To.TO.TO_, values);
    }

    /**
     * Fetch records that have <code>read_state IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchByReadState(UInteger... values) {
        return fetch(To.TO.READ_STATE, values);
    }

    /**
     * Fetch records that have <code>read_time IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchByReadTime(LocalDateTime... values) {
        return fetch(To.TO.READ_TIME, values);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.To> fetchByDeleted(UInteger... values) {
        return fetch(To.TO.DELETED, values);
    }
}