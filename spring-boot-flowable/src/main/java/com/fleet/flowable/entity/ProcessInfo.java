package com.fleet.flowable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 流程信息
 */
public class ProcessInfo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实例id
     */
    private String processInstanceId;

    /**
     * 流程类型
     */
    private String processDefinitionKey;

    /**
     * 流程编号（唯一）
     */
    private String businessKey;

    /**
     * 流程名称
     */
    private String processName;

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
     * 当前节点审批人
     */
    private String assignee;

    /**
     * 审批人
     */
    private Map<String, String> assignees;

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
     * 流程状态（1：进行中，2：已结案，3：已终止）
     */
    private Integer state;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Map<String, String> getAssignees() {
        return assignees;
    }

    public void setAssignees(Map<String, String> assignees) {
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
