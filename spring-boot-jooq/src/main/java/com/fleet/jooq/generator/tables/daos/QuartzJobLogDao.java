/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.daos;


import com.fleet.jooq.generator.tables.QuartzJobLog;
import com.fleet.jooq.generator.tables.records.QuartzJobLogRecord;

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
public class QuartzJobLogDao extends DAOImpl<QuartzJobLogRecord, com.fleet.jooq.generator.tables.pojos.QuartzJobLog, UInteger> {

    /**
     * Create a new QuartzJobLogDao without any configuration
     */
    public QuartzJobLogDao() {
        super(QuartzJobLog.QUARTZ_JOB_LOG, com.fleet.jooq.generator.tables.pojos.QuartzJobLog.class);
    }

    /**
     * Create a new QuartzJobLogDao with an attached configuration
     */
    public QuartzJobLogDao(Configuration configuration) {
        super(QuartzJobLog.QUARTZ_JOB_LOG, com.fleet.jooq.generator.tables.pojos.QuartzJobLog.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UInteger getId(com.fleet.jooq.generator.tables.pojos.QuartzJobLog object) {
        return object.getJobLogId();
    }

    /**
     * Fetch records that have <code>job_log_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByJobLogId(UInteger... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.JOB_LOG_ID, values);
    }

    /**
     * Fetch a unique record that has <code>job_log_id = value</code>
     */
    public com.fleet.jooq.generator.tables.pojos.QuartzJobLog fetchOneByJobLogId(UInteger value) {
        return fetchOne(QuartzJobLog.QUARTZ_JOB_LOG.JOB_LOG_ID, value);
    }

    /**
     * Fetch records that have <code>job_id IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByJobId(UInteger... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.JOB_ID, values);
    }

    /**
     * Fetch records that have <code>job_name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByJobName(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.JOB_NAME, values);
    }

    /**
     * Fetch records that have <code>bean_name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByBeanName(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.BEAN_NAME, values);
    }

    /**
     * Fetch records that have <code>method_name IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByMethodName(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.METHOD_NAME, values);
    }

    /**
     * Fetch records that have <code>param IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByParam(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.PARAM, values);
    }

    /**
     * Fetch records that have <code>cron_expression IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByCronExpression(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.CRON_EXPRESSION, values);
    }

    /**
     * Fetch records that have <code>state IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByState(Integer... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.STATE, values);
    }

    /**
     * Fetch records that have <code>error IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByError(String... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.ERROR, values);
    }

    /**
     * Fetch records that have <code>millis IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByMillis(Long... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.MILLIS, values);
    }

    /**
     * Fetch records that have <code>create_time IN (values)</code>
     */
    public List<com.fleet.jooq.generator.tables.pojos.QuartzJobLog> fetchByCreateTime(LocalDateTime... values) {
        return fetch(QuartzJobLog.QUARTZ_JOB_LOG.CREATE_TIME, values);
    }
}