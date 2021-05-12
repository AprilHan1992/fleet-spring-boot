package com.fleet.flowable.listener;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

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
