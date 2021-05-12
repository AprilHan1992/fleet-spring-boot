package com.fleet.activiti5.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 转交、委派记录
 *
 * @author April Han
 */
public class TurnLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务节点key
     */
    private String taskDefinitionKey;

    /**
     * 任务节点名称
     */
    private String taskName;

    /**
     * 节点审批人
     */
    private String assignee;

    /**
     * 审批操作（同意，驳回，退回，终止，结案，转交，委派，委派完成，退出，挂起）
     */
    private String handle;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}
