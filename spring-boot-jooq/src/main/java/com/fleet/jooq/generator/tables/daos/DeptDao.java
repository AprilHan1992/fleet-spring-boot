/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.Dept;
import com.fleet.jooq.generator.tables.records.DeptRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.types.UInteger;


/**
 * 部门管理
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DeptDao extends DAOImpl<DeptRecord, com.fleet.jooq.generator.tables.pojos.Dept, UInteger> {

    /**
     * Create a new DeptDao without any configuration
     */
    public DeptDao() {
        super(Dept.DEPT, com.fleet.jooq.generator.tables.pojos.Dept.class);
    }

    /**
     * Create a new DeptDao with an attached configuration
     */
    public DeptDao(Configuration configuration) {
        super(Dept.DEPT, com.fleet.jooq.generator.tables.pojos.Dept.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.fleet.jooq.generator.tables.pojos.Dept object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchById(UInteger... values) {
        return fetch(Dept.DEPT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.Dept fetchOneById(UInteger value) {
        return fetchOne(Dept.DEPT.ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByName(String... values) {
        return fetch(Dept.DEPT.NAME, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByState(UInteger... values) {
        return fetch(Dept.DEPT.STATE, values);
    }

    /**
     * Fetch records that have <code>sort IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchBySort(UInteger... values) {
        return fetch(Dept.DEPT.SORT, values);
    }

    /**
     * Fetch records that have <code>creator_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByCreatorId(UInteger... values) {
        return fetch(Dept.DEPT.CREATOR_ID, values);
    }

    /**
     * Fetch records that have <code>create_time IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByCreateTime(LocalDateTime... values) {
        return fetch(Dept.DEPT.CREATE_TIME, values);
    }

    /**
     * Fetch records that have <code>editor_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByEditorId(UInteger... values) {
        return fetch(Dept.DEPT.EDITOR_ID, values);
    }

    /**
     * Fetch records that have <code>edit_time IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByEditTime(LocalDateTime... values) {
        return fetch(Dept.DEPT.EDIT_TIME, values);
    }

    /**
     * Fetch records that have <code>upper_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByUpperId(UInteger... values) {
        return fetch(Dept.DEPT.UPPER_ID, values);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.Dept> fetchByDeleted(UInteger... values) {
        return fetch(Dept.DEPT.DELETED, values);
    }
}
