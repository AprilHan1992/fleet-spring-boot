/*
 * This file is generated by jOOQ.
 */
package com.fleet.jooq.generator.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

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
public class QuartzJob implements Serializable {

    private static final long serialVersionUID = 402131128;

    private UInteger id;
    private String   jobName;
    private String   beanName;
    private String   methodName;
    private String   param;
    private String   cronExpression;
    private UInteger enabled;
    private String   remark;
    private UInteger deleted;

    public QuartzJob() {}

    public QuartzJob(QuartzJob value) {
        this.id = value.id;
        this.jobName = value.jobName;
        this.beanName = value.beanName;
        this.methodName = value.methodName;
        this.param = value.param;
        this.cronExpression = value.cronExpression;
        this.enabled = value.enabled;
        this.remark = value.remark;
        this.deleted = value.deleted;
    }

    public QuartzJob(
        UInteger id,
        String   jobName,
        String   beanName,
        String   methodName,
        String   param,
        String   cronExpression,
        UInteger enabled,
        String   remark,
        UInteger deleted
    ) {
        this.id = id;
        this.jobName = jobName;
        this.beanName = beanName;
        this.methodName = methodName;
        this.param = param;
        this.cronExpression = cronExpression;
        this.enabled = enabled;
        this.remark = remark;
        this.deleted = deleted;
    }

    public UInteger getId() {
        return this.id;
    }

    public void setId(UInteger id) {
        this.id = id;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public UInteger getEnabled() {
        return this.enabled;
    }

    public void setEnabled(UInteger enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UInteger getDeleted() {
        return this.deleted;
    }

    public void setDeleted(UInteger deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QuartzJob (");

        sb.append(id);
        sb.append(", ").append(jobName);
        sb.append(", ").append(beanName);
        sb.append(", ").append(methodName);
        sb.append(", ").append(param);
        sb.append(", ").append(cronExpression);
        sb.append(", ").append(enabled);
        sb.append(", ").append(remark);
        sb.append(", ").append(deleted);

        sb.append(")");
        return sb.toString();
    }
}
