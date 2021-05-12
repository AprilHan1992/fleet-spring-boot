package com.fleet.activiti5.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Map;

/**
 * @author April Han
 */
public class AssigneeTaskListener implements TaskListener {

    private static final long serialVersionUID = 1L;

    /**
     * 设置审批人
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> assignees = (Map<String, Object>) delegateTask.getVariable("assignees");
        delegateTask.setAssignee(assignees.get(delegateTask.getTaskDefinitionKey()).toString());
    }
}
