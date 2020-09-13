package com.fleet.flowable.entity;

/**
 * 转交、指定、委派信息
 */
public class Turn {

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
