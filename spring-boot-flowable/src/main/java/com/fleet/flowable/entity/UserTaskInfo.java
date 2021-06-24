package com.fleet.flowable.entity;

import java.io.Serializable;

/**
 * @author April Han
 */
public class UserTaskInfo implements Serializable {

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
     * 当前节点审批人
     */
    private String assignee;

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
}
