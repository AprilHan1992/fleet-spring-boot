/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.UserCopyCopy;
import com.fleet.jooq.generator.tables.records.UserCopyCopyRecord;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
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
public class UserCopyCopyDao extends DAOImpl<UserCopyCopyRecord, com.fleet.jooq.generator.tables.pojos.UserCopyCopy, UInteger> {

    /**
     * Create a new UserCopyCopyDao without any configuration
     */
    public UserCopyCopyDao() {
        super(UserCopyCopy.USER_COPY_COPY, com.fleet.jooq.generator.tables.pojos.UserCopyCopy.class);
    }

    /**
     * Create a new UserCopyCopyDao with an attached configuration
     */
    public UserCopyCopyDao(Configuration configuration) {
        super(UserCopyCopy.USER_COPY_COPY, com.fleet.jooq.generator.tables.pojos.UserCopyCopy.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.fleet.jooq.generator.tables.pojos.UserCopyCopy object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchById(UInteger... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.UserCopyCopy fetchOneById(UInteger value) {
        return fetchOne(UserCopyCopy.USER_COPY_COPY.ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByName(String... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.UserCopyCopy fetchOneByName(String value) {
        return fetchOne(UserCopyCopy.USER_COPY_COPY.NAME, value);
    }

    /**
     * Fetch records that have <code>nick_name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByNickName(String... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.NICK_NAME, values);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByEmail(String... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.EMAIL, values);
    }

    /**
     * Fetch records that have <code>pwd IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByPwd(String... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.PWD, values);
    }

    /**
     * Fetch records that have <code>pwd_salt IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByPwdSalt(String... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.PWD_SALT, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByState(UInteger... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.STATE, values);
    }

    /**
     * Fetch records that have <code>reg_time IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByRegTime(LocalDateTime... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.REG_TIME, values);
    }

    /**
     * Fetch records that have <code>deleted IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.UserCopyCopy> fetchByDeleted(UInteger... values) {
        return fetch(UserCopyCopy.USER_COPY_COPY.DELETED, values);
    }
}
