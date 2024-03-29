/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.records;


import com.fleet.jooq.generator.tables.Job;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
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
public class JobRecord extends UpdatableRecordImpl<JobRecord> implements Record8<UInteger, String, UInteger, UInteger, LocalDateTime, LocalDateTime, UInteger, UInteger> {

    private static final long serialVersionUID = 460375013;

    /**
     * Setter for <code>fleet-test.job.job_id</code>. 岗位id
     */
    public void setJobId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>fleet-test.job.job_id</code>. 岗位id
     */
    public UInteger getJobId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>fleet-test.job.job_name</code>. 岗位名称
     */
    public void setJobName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>fleet-test.job.job_name</code>. 岗位名称
     */
    public String getJobName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>fleet-test.job.job_status</code>. 岗位状态（1：正常， 0：停用）
     */
    public void setJobStatus(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>fleet-test.job.job_status</code>. 岗位状态（1：正常， 0：停用）
     */
    public UInteger getJobStatus() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>fleet-test.job.sort_value</code>. 排序（数字越大，越排前）
     */
    public void setSortValue(UInteger value) {
        set(3, value);
    }

    /**
     * Getter for <code>fleet-test.job.sort_value</code>. 排序（数字越大，越排前）
     */
    public UInteger getSortValue() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>fleet-test.job.create_time</code>. 创建时间
     */
    public void setCreateTime(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>fleet-test.job.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>fleet-test.job.edit_time</code>. 更新时间
     */
    public void setEditTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>fleet-test.job.edit_time</code>. 更新时间
     */
    public LocalDateTime getEditTime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>fleet-test.job.deleted</code>. 是否删除（1：是，0：否）
     */
    public void setDeleted(UInteger value) {
        set(6, value);
    }

    /**
     * Getter for <code>fleet-test.job.deleted</code>. 是否删除（1：是，0：否）
     */
    public UInteger getDeleted() {
        return (UInteger) get(6);
    }

    /**
     * Setter for <code>fleet-test.job.upper_id</code>. 上一级岗位id
     */
    public void setUpperId(UInteger value) {
        set(7, value);
    }

    /**
     * Getter for <code>fleet-test.job.upper_id</code>. 上一级岗位id
     */
    public UInteger getUpperId() {
        return (UInteger) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<UInteger, String, UInteger, UInteger, LocalDateTime, LocalDateTime, UInteger, UInteger> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<UInteger, String, UInteger, UInteger, LocalDateTime, LocalDateTime, UInteger, UInteger> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return Job.JOB.JOB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Job.JOB.JOB_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return Job.JOB.JOB_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return Job.JOB.SORT_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field5() {
        return Job.JOB.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return Job.JOB.EDIT_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field7() {
        return Job.JOB.DELETED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field8() {
        return Job.JOB.UPPER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component1() {
        return getJobId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component3() {
        return getJobStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component4() {
        return getSortValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component5() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime component6() {
        return getEditTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component7() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger component8() {
        return getUpperId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getJobId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getJobStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getSortValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value5() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getEditTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value7() {
        return getDeleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value8() {
        return getUpperId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value1(UInteger value) {
        setJobId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value2(String value) {
        setJobName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value3(UInteger value) {
        setJobStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value4(UInteger value) {
        setSortValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value5(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value6(LocalDateTime value) {
        setEditTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value7(UInteger value) {
        setDeleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord value8(UInteger value) {
        setUpperId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobRecord values(UInteger value1, String value2, UInteger value3, UInteger value4, LocalDateTime value5, LocalDateTime value6, UInteger value7, UInteger value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JobRecord
     */
    public JobRecord() {
        super(Job.JOB);
    }

    /**
     * Create a detached, initialised JobRecord
     */
    public JobRecord(UInteger jobId, String jobName, UInteger jobStatus, UInteger sortValue, LocalDateTime createTime, LocalDateTime editTime, UInteger deleted, UInteger upperId) {
        super(Job.JOB);

        set(0, jobId);
        set(1, jobName);
        set(2, jobStatus);
        set(3, sortValue);
        set(4, createTime);
        set(5, editTime);
        set(6, deleted);
        set(7, upperId);
    }
}
