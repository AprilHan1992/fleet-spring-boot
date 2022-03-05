package com.fleet.activiti5.listener;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author April Han
 */
public class SignerTaskListener implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        String executionId = delegateTask.getExecutionId();

        String handle = (String) runtimeService.getVariable(executionId, "操作");
        runtimeService.setVariable(executionId, "操作", handle);
    }
}
