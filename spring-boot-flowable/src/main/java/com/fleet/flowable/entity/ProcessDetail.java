package com.fleet.flowable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 流程信息
 *
 * @author April Han
 */
public class ProcessDetail<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实例id
     */
    private String instanceId;

    /**
     * 流程类型
     */
    private String definitionKey;

    /**
     * 流程编号（唯一）
     */
    private String businessKey;

    /**
     * 流程名称
     */
    private String title;

    /**
     * 流程发起人
     */
    private String initiator;

    /**
     * 流程发起人电话
     */
    private String phone;

    /**
     * 流程发起人邮箱
     */
    private String email;

    /**
     * 流程详情
     */
    T details;

    /**
     * 流程备注
     */
    private String remark;

    /**
     * 审批人
     */
    private Map<String, Object> assignees;

    /**
     * 流程发起时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 流程结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 流程状态（1：审批中，2：已结案，3：已终止）
     */
    private Integer state;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getDefinitionKey() {
        return definitionKey;
    }

    public void setDefinitionKey(String definitionKey) {
        this.definitionKey = definitionKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getAssignees() {
        return assignees;
    }

    public void setAssignees(Map<String, Object> assignees) {
        this.assignees = assignees;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
