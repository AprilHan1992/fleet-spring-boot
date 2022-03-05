package com.fleet.activiti6.entity;

/**
 * 审批信息
 *
 * @author April Han
 */
public class Approval {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 审批操作（同意，驳回，退回，终止，结案，转交，退出，挂起）
     */
    private String handle;

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
}
