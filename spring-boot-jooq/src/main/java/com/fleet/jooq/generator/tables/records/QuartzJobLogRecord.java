/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.QuartzJobLog;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;
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
public class QuartzJobLogRecord extends UpdatableRecordImpl<QuartzJobLogRecord> implements Record11<UInteger, UInteger, String, String, String, String, String, Integer, String, Long, LocalDateTime> {

    private static final long serialVersionUID = 1770900166;

    /**
     * Setter for <code>fleet-test.quartz_job_log.job_log_id</code>. 日志id
     */
    public void setJobLogId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.job_log_id</code>. 日志id
     */
    public UInteger getJobLogId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.job_id</code>. 定时器id
     */
    public void setJobId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.job_id</code>. 定时器id
     */
    public UInteger getJobId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.job_name</code>. 定时器名称
     */
    public void setJobName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.job_name</code>. 定时器名称
     */
    public String getJobName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.bean_name</code>. Bean名称
     */
    public void setBeanName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.bean_name</code>. Bean名称
     */
    public String getBeanName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.method_name</code>. 方法名称
     */
    public void setMethodName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.method_name</code>. 方法名称
     */
    public String getMethodName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.param</code>. 参数
     */
    public void setParam(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.param</code>. 参数
     */
    public String getParam() {
        return (String) get(5);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.cron_expression</code>. cron表达式
     */
    public void setCronExpression(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.cron_expression</code>. cron表达式
     */
    public String getCronExpression() {
        return (String) get(6);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.state</code>. 任务状态 （1：成功，0：失败）
     */
    public void setState(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.state</code>. 任务状态 （1：成功，0：失败）
     */
    public Integer getState() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.error</code>. 失败信息
     */
    public void setError(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.error</code>. 失败信息
     */
    public String getError() {
        return (String) get(8);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.millis</code>. 执行时间（单位：毫秒）
     */
    public void setMillis(Long value) {
        set(9, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.millis</code>. 执行时间（单位：毫秒）
     */
    public Long getMillis() {
        return (Long) get(9);
    }

    /**
     * Setter for <code>fleet-test.quartz_job_log.create_time</code>. 创建时间
     */
    public void setCreateTime(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>fleet-test.quartz_job_log.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<UInteger, UInteger, String, String, String, String, String, Integer, String, Long, LocalDateTime> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<UInteger, UInteger, String, String, String, String, String, Integer, String, Long, LocalDateTime> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return QuartzJobLog.QUARTZ_JOB_LOG.JOB_LOG_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return QuartzJobLog.QUARTZ_JOB_LOG.JOB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return QuartzJobLog.QUARTZ_JOB_LOG.JOB_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return QuartzJobLog.QUARTZ_JOB_LOG.BEAN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return QuartzJobLog.QUARTZ_JOB_LOG.METHOD_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return QuartzJobLog.QUARTZ_JOB_LOG.PARAM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return QuartzJobLog.QUARTZ_JOB_LOG.CRON_EXPRESSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return QuartzJobLog.QUARTZ_JOB_LOG.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return QuartzJobLog.QUARTZ_JOB_LOG.ERROR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field10() {
        return QuartzJobLog.QUARTZ_JOB_LOG.MILLIS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field11() {
        return QuartzJobLog.QUARTZ_JOB_LOG.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getJobLogId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component2() {
        return getJobId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getBeanName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getParam();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getCronExpression();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component8() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component9() {
        return getError();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component10() {
        return getMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component11() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getJobLogId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getJobId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getBeanName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getMethodName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getParam();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getCronExpression();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getError();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value10() {
        return getMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value11() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value1(UInteger value) {
        setJobLogId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value2(UInteger value) {
        setJobId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value3(String value) {
        setJobName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value4(String value) {
        setBeanName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value5(String value) {
        setMethodName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value6(String value) {
        setParam(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value7(String value) {
        setCronExpression(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value8(Integer value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value9(String value) {
        setError(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value10(Long value) {
        setMillis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord value11(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuartzJobLogRecord values(UInteger value1, UInteger value2, String value3, String value4, String value5, String value6, String value7, Integer value8, String value9, Long value10, LocalDateTime value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QuartzJobLogRecord
     */
    public QuartzJobLogRecord() {
        super(QuartzJobLog.QUARTZ_JOB_LOG);
    }

    /**
     * Create a detached, initialised QuartzJobLogRecord
     */
    public QuartzJobLogRecord(UInteger jobLogId, UInteger jobId, String jobName, String beanName, String methodName, String param, String cronExpression, Integer state, String error, Long millis, LocalDateTime createTime) {
        super(QuartzJobLog.QUARTZ_JOB_LOG);

        set(0, jobLogId);
        set(1, jobId);
        set(2, jobName);
        set(3, beanName);
        set(4, methodName);
        set(5, param);
        set(6, cronExpression);
        set(7, state);
        set(8, error);
        set(9, millis);
        set(10, createTime);
    }
}
