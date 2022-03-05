package com.fleet.activiti6.entity;

import java.io.Serializable;

/**
 * 转交、指定、委派信息
 *
 * @author April Han
 */
public class Turn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 转交人
     */
    private String assignee;

    /**
     * 审批意见
     */
    private String remark;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
