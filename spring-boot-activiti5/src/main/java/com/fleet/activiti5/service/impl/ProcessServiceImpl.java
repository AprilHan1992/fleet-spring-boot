package com.fleet.activiti5.service.impl;

import com.fleet.activiti5.entity.*;
import com.fleet.activiti5.handler.BaseException;
import com.fleet.activiti5.page.PageUtil;
import com.fleet.activiti5.page.entity.Page;
import com.fleet.activiti5.service.ProcessService;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * @author April Han
 */
@Transactional
@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private IdentityService identityService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessEngineConfiguration processEngineConfiguration;

    @Override
    public PageUtil<TaskDetail<?>> myTaskList(String userId, String initiator, String title, String definitionKey, String definitionName, Page page) {
        PageUtil<TaskDetail<?>> pageUtil = new PageUtil<>();
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee(userId);
        if (StringUtils.isNotEmpty(initiator)) {
            taskQuery.processVariableValueEquals("initiator", initiator);
        }
        if (StringUtils.isNotEmpty(title)) {
            taskQuery.processVariableValueLike("title", "%" + title + "%");
        }
        if (StringUtils.isNotEmpty(definitionKey)) {
            taskQuery.processDefinitionKey(definitionKey);
        }
        if (StringUtils.isNotEmpty(definitionName)) {
            taskQuery.processDefinitionName(definitionName);
        }
        taskQuery.orderByTaskCreateTime().asc();

        long count = taskQuery.count();
        page.setTotalRows((int) count);

        List<TaskDetail<?>> taskDetailList = new ArrayList<>();
        List<Task> taskList = taskQuery.listPage(page.getFromIndex(), page.getPageRows());
        if (taskList != null) {
            for (Task task : taskList) {
                TaskDetail<?> taskDetail = getTaskDetail(task);
                taskDetailList.add(taskDetail);
            }
        }

        pageUtil.setList(taskDetailList);
        pageUtil.setPage(page);
        return pageUtil;
    }

    private TaskDetail<?> getTaskDetail(Task task) {
        String taskId = task.getId();
        String instanceId = task.getProcessInstanceId();

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        ProcessDetail<?> processDetail = (ProcessDetail<?>) taskService.getVariable(taskId, "processDetail");

        TaskDetail<Object> taskDetail = new TaskDetail<>();
        taskDetail.setInstanceId(instanceId);
        taskDetail.setDefinitionKey(processDetail.getDefinitionKey());
        taskDetail.setBusinessKey(processDetail.getBusinessKey());
        taskDetail.setTitle(processDetail.getTitle());
        taskDetail.setInitiator(processDetail.getInitiator());
        taskDetail.setPhone(processDetail.getPhone());
        taskDetail.setEmail(processDetail.getEmail());
        taskDetail.setDetails(processDetail.getDetails());
        taskDetail.setRemark(processDetail.getRemark());
        taskDetail.setStartTime(historicProcessInstance.getStartTime());
        taskDetail.setTaskId(taskId);
        taskDetail.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskDetail.setTaskName(task.getName());
        taskDetail.setCreateTime(task.getCreateTime());
        taskDetail.setAssignee(task.getAssignee());
        return taskDetail;
    }

    @Override
    public PageUtil<ProcessDetail<?>> myAppliedList(String userId, String assignee, String title, String definitionKey, String definitionName, String state, Page page) {
        PageUtil<ProcessDetail<?>> pageUtil = new PageUtil<>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.variableValueEquals("initiator", userId);
        if (StringUtils.isNotEmpty(assignee)) {
            historicProcessInstanceQuery.involvedUser(assignee);
        }
        if (StringUtils.isNotEmpty(title)) {
            historicProcessInstanceQuery.variableValueLike("title", "%" + title + "%");
        }
        if (StringUtils.isNotEmpty(definitionKey)) {
            historicProcessInstanceQuery.processDefinitionKey(definitionKey);
        }
        if (StringUtils.isNotEmpty(definitionName)) {
            historicProcessInstanceQuery.processDefinitionName(definitionName);
        }
        if (StringUtils.isNotEmpty(state)) {
            if ("1".equals(state)) {
                historicProcessInstanceQuery.unfinished();
            } else if ("2".equals(state)) {
                historicProcessInstanceQuery.variableValueEquals("terminated", "false");
                historicProcessInstanceQuery.finished();
            } else if ("3".equals(state)) {
                historicProcessInstanceQuery.variableValueEquals("terminated", "true");
                historicProcessInstanceQuery.finished();
            }
        }
        historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc();

        long count = historicProcessInstanceQuery.count();
        page.setTotalRows((int) count);

        List<ProcessDetail<?>> processDetailList = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.listPage(page.getFromIndex(), page.getPageRows());
        if (historicProcessInstanceList != null) {
            for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
                ProcessDetail<?> processDetail = getProcessDetail(historicProcessInstance);
                processDetailList.add(processDetail);
            }
        }

        pageUtil.setList(processDetailList);
        pageUtil.setPage(page);
        return pageUtil;
    }

    @Override
    public PageUtil<ProcessDetail<?>> myApprovedList(String userId, String initiator, String title, String definitionKey, String definitionName, String state, Page page) {
        PageUtil<ProcessDetail<?>> pageUtil = new PageUtil<>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.involvedUser(userId);
        if (StringUtils.isNotEmpty(initiator)) {
            historicProcessInstanceQuery.variableValueEquals("initiator", initiator);
        }
        if (StringUtils.isNotEmpty(title)) {
            historicProcessInstanceQuery.variableValueLike("title", "%" + title + "%");
        }
        if (StringUtils.isNotEmpty(definitionKey)) {
            historicProcessInstanceQuery.processDefinitionKey(definitionKey);
        }
        if (StringUtils.isNotEmpty(definitionName)) {
            historicProcessInstanceQuery.processDefinitionName(definitionName);
        }
        if (StringUtils.isNotEmpty(state)) {
            if ("1".equals(state)) {
                historicProcessInstanceQuery.unfinished();
            } else if ("2".equals(state)) {
                historicProcessInstanceQuery.variableValueEquals("terminated", "false");
                historicProcessInstanceQuery.finished();
            } else if ("3".equals(state)) {
                historicProcessInstanceQuery.variableValueEquals("terminated", "true");
                historicProcessInstanceQuery.finished();
            }
        }
        historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc();

        long count = historicProcessInstanceQuery.count();
        page.setTotalRows((int) count);

        List<ProcessDetail<?>> processDetailList = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.listPage(page.getFromIndex(), page.getPageRows());
        if (historicProcessInstanceList != null) {
            for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
                ProcessDetail<?> processDetail = getProcessDetail(historicProcessInstance);
                processDetailList.add(processDetail);
            }
        }

        pageUtil.setList(processDetailList);
        pageUtil.setPage(page);
        return pageUtil;
    }

    private ProcessDetail<?> getProcessDetail(HistoricProcessInstance historicProcessInstance) {
        String instanceId = historicProcessInstance.getId();

        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(instanceId)
                .variableName("processDetail")
                .singleResult();
        if (historicVariableInstance == null) {
            return null;
        }

        ProcessDetail<?> processDetail = (ProcessDetail<?>) historicVariableInstance.getValue();
        processDetail.setInstanceId(instanceId);
        processDetail.setStartTime(historicProcessInstance.getStartTime());
        processDetail.setEndTime(historicProcessInstance.getEndTime());

        if (historicProcessInstance.getEndTime() == null) {
            processDetail.setState(1);
        } else {
            // 查询流程是否终止
            HistoricVariableInstance terminated = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(instanceId)
                    .variableValueEquals("terminated", "true")
                    .singleResult();
            if (terminated != null) {
                processDetail.setState(3);
            } else {
                processDetail.setState(2);
            }
        }

        return processDetail;
    }

    @Override
    public Long getTotal(String definitionKey) {
        return historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(definitionKey)
                .count();
    }

    @Override
    public String generateBusinessKey(String definitionKey) {
        long total = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(definitionKey)
                .count();
        return definitionKey + ":" + total;
    }

    @Override
    public TaskDetail<?> start(ProcessDetail<?> processDetail) {
        String definitionKey = processDetail.getDefinitionKey();
        String businessKey = processDetail.getBusinessKey();
        // 先判断business的唯一性
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance != null) {
            throw new BaseException("businessKey:" + businessKey + "已存在");
        }

        identityService.setAuthenticatedUserId(processDetail.getInitiator());

        Map<String, Object> variables = new HashMap<>();
        variables.put("definitionKey", definitionKey);
        variables.put("businessKey", businessKey);
        variables.put("title", processDetail.getTitle());
        variables.put("initiator", processDetail.getInitiator());
        variables.put("phone", processDetail.getPhone());
        variables.put("email", processDetail.getEmail());
        variables.put("details", processDetail.getDetails());
        variables.put("remark", processDetail.getRemark());
        variables.put("assignees", processDetail.getAssignees());
        variables.put("processDetail", processDetail);

        if ("xjsq".equals(definitionKey)) {
            variables.put("days", processDetail.getDetails());
        }

        if ("qksq".equals(definitionKey)) {
            variables.put("signerList", processDetail.getAssignees().get("signerList"));
        }

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(definitionKey, businessKey, variables);
        if (processInstance == null) {
            throw new BaseException("流程创建失败");
        }

        String instanceId = processInstance.getId();
        Task task = taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .singleResult();

        historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance == null) {
            throw new BaseException("流程为空");
        }

        TaskDetail<Object> taskDetail = new TaskDetail<>();
        taskDetail.setInstanceId(instanceId);
        taskDetail.setDefinitionKey(definitionKey);
        taskDetail.setBusinessKey(businessKey);
        taskDetail.setTitle(processDetail.getTitle());
        taskDetail.setInitiator(processDetail.getInitiator());
        taskDetail.setPhone(processDetail.getPhone());
        taskDetail.setEmail(processDetail.getEmail());
        taskDetail.setDetails(processDetail.getDetails());
        taskDetail.setRemark(processDetail.getRemark());
        taskDetail.setStartTime(historicProcessInstance.getStartTime());
        taskDetail.setTaskId(task.getId());
        taskDetail.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskDetail.setTaskName(task.getName());
        taskDetail.setCreateTime(task.getCreateTime());
        taskDetail.setAssignee(task.getAssignee());
        return taskDetail;
    }

    @Override
    public Boolean apply(ProcessDetail<?> processDetail) {
        TaskDetail<?> taskDetail = start(processDetail);
        if (taskDetail == null) {
            throw new BaseException("流程创建失败");
        }

        String taskId = taskDetail.getTaskId();
        String instanceId = taskDetail.getInstanceId();

        List<String> taskHandleList = getTaskHandleList(taskId);
        if (!taskHandleList.contains("提交")) {
            throw new BaseException("任务节点没有“提交”操作");
        }

        identityService.setAuthenticatedUserId(taskDetail.getAssignee());

        Map<String, Object> variables = new HashMap<>();
        variables.put("操作", "提交");
        taskService.setVariablesLocal(taskId, variables);
        taskService.addComment(taskId, instanceId, processDetail.getRemark());
        variables.put("terminated", "false");
        taskService.complete(taskId, variables);
        return true;
    }

    @Override
    public Boolean reapply(String taskId, ProcessDetail<?> processDetail) {
        String definitionKey = processDetail.getDefinitionKey();
        String businessKey = processDetail.getBusinessKey();

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        String instanceId = task.getProcessInstanceId();

        identityService.setAuthenticatedUserId(processDetail.getInitiator());

        Map<String, Object> variables = new HashMap<>();
        variables.put("操作", "重新提交");
        taskService.setVariablesLocal(taskId, variables);
        taskService.addComment(taskId, instanceId, processDetail.getRemark());
        variables.put("definitionKey", definitionKey);
        variables.put("businessKey", businessKey);
        variables.put("title", processDetail.getTitle());
        variables.put("initiator", processDetail.getInitiator());
        variables.put("phone", processDetail.getPhone());
        variables.put("email", processDetail.getEmail());
        variables.put("details", processDetail.getDetails());
        variables.put("remark", processDetail.getRemark());
        variables.put("assignees", processDetail.getAssignees());
        variables.put("processDetail", processDetail);

        if ("xjsq".equals(definitionKey)) {
            variables.put("days", processDetail.getDetails());
        }

        if ("qksq".equals(definitionKey)) {
            variables.put("signerList", processDetail.getAssignees().get("signerList"));
        }

        variables.put("terminated", "false");
        taskService.complete(taskId, variables);
        return true;
    }

    @Override
    public Boolean completeTask(Approval approval) {
        String taskId = approval.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        String instanceId = task.getProcessInstanceId();

        List<String> taskHandleList = getTaskHandleList(taskId);
        if (!taskHandleList.contains(approval.getHandle())) {
            throw new BaseException("任务节点没有“" + approval.getHandle() + "”操作");
        }

        identityService.setAuthenticatedUserId(task.getAssignee());

        Map<String, Object> variables = new HashMap<>();
        variables.put("操作", approval.getHandle());
        taskService.setVariablesLocal(taskId, variables);
        taskService.addComment(taskId, instanceId, approval.getRemark());
        variables.put("terminated", "false");
        taskService.complete(taskId, variables);
        return true;
    }

    @Override
    public Boolean resetAssignees(String taskId, Map<String, Object> assignees) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        Map<String, Object> oAssignees = (Map<String, Object>) taskService.getVariable(taskId, "assignees");
        ProcessDetail<?> processDetail = (ProcessDetail<?>) taskService.getVariable(taskId, "processDetail");
        for (String key : assignees.keySet()) {
            oAssignees.put(key, assignees.get(key));
        }
        processDetail.setAssignees(oAssignees);

        Map<String, Object> variables = new HashMap<>();
        variables.put("assignees", oAssignees);
        variables.put("processDetail", processDetail);
        taskService.setVariables(taskId, variables);

        if (task.getTaskDefinitionKey() != null) {
            if (assignees.containsKey(task.getTaskDefinitionKey())) {
                if (assignees.get(task.getTaskDefinitionKey()) != null && !assignees.get(task.getTaskDefinitionKey()).equals(task.getAssignee())) {
                    Turn turn = new Turn();
                    turn.setTaskId(taskId);
                    turn.setAssignee(assignees.get(task.getTaskDefinitionKey()).toString());
                    turn.setRemark("修改审批人后转交给新的审批人");
                    turnTask(turn);
                }
            }
        }
        return true;
    }

    @Override
    public Boolean stop(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            throw new BaseException("businessKey:" + businessKey + "不存在或流程已结案，无法终止");
        }

        String definitionId = processInstance.getProcessDefinitionId();

        ProcessDefinitionEntity processDefinitionEntity = getProcessDefinitionEntity(definitionId);
        ActivityImpl endActivity = getEndActivityImpl(processDefinitionEntity);
        if (endActivity == null) {
            throw new BaseException("无结束事件");
        }

        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .list();
        if (taskList == null || taskList.size() == 0) {
            throw new BaseException("任务不存在");
        } else if (taskList.size() > 1) {
            throw new BaseException("只允许在申请节点终止流程");
        } else {
            Task task = taskList.get(0);
            String taskId = task.getId();
            String instanceId = task.getProcessInstanceId();
            String activityId = task.getTaskDefinitionKey();

            if (!"apply".equals(activityId)) {
                throw new BaseException("只允许在申请节点终止流程");
            }

            ActivityImpl curActivity = getActivityImpl(processDefinitionEntity, activityId);
            curActivity.getOutgoingTransitions().clear();
            // 创建新流向
            TransitionImpl transition = curActivity.createOutgoingTransition();
            transition.setDestination(endActivity);

            identityService.setAuthenticatedUserId(task.getAssignee());

            Map<String, Object> variables = new HashMap<>();
            variables.put("操作", "终止");
            taskService.setVariablesLocal(taskId, variables);
            taskService.addComment(taskId, instanceId, "终止");
            variables.put("terminated", "true");
            taskService.complete(taskId, variables);
        }
        return true;
    }

    private ActivityImpl getActivityImpl(ProcessDefinitionEntity processDefinitionEntity, String activityId) {
        return processDefinitionEntity.findActivity(activityId);
    }

    private ActivityImpl getEndActivityImpl(ProcessDefinitionEntity processDefinitionEntity) {
        List<ActivityImpl> activities = processDefinitionEntity.getActivities();
        for (ActivityImpl activity : activities) {
            if ("endEvent".equals(activity.getProperty("type").toString())) {
                return activity;
            }
        }
        return null;
    }

    private ProcessDefinitionEntity getProcessDefinitionEntity(String definitionId) {
        return (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(definitionId);
    }

    @Override
    public Boolean withdraw(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            throw new BaseException("businessKey:" + businessKey + "不存在或流程已结案，无法撤回");
        }

        String definitionId = processInstance.getProcessDefinitionId();

        ProcessDefinitionEntity processDefinitionEntity = getProcessDefinitionEntity(definitionId);
        ActivityImpl applyActivity = getActivityImpl(processDefinitionEntity, "apply");
        if (applyActivity == null) {
            throw new BaseException("无申请节点事件");
        }

        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .list();
        if (taskList == null || taskList.size() == 0) {
            throw new BaseException("任务不存在");
        }

        for (Task task : taskList) {
            String taskId = task.getId();
            String instanceId = task.getProcessInstanceId();
            String activityId = task.getTaskDefinitionKey();

            if ("apply".equals(activityId)) {
                continue;
            }

            ActivityImpl curActivity = getActivityImpl(processDefinitionEntity, activityId);
            curActivity.getOutgoingTransitions().clear();
            // 创建新流向
            TransitionImpl transition = curActivity.createOutgoingTransition();
            transition.setDestination(applyActivity);

            identityService.setAuthenticatedUserId(task.getAssignee());

            Map<String, Object> variables = new HashMap<>();
            variables.put("操作", "撤回");
            taskService.setVariablesLocal(taskId, variables);
            taskService.addComment(taskId, instanceId, "撤回");
            variables.put("terminated", "false");
            taskService.complete(taskId, variables);
        }
        return true;
    }

    @Override
    public Boolean delete(String businessKey) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance == null) {
            throw new BaseException("businessKey:" + businessKey + "不存在");
        }

        String instanceId = historicProcessInstance.getId();
        // 流程未结束
        if (historicProcessInstance.getEndTime() == null) {
            runtimeService.deleteProcessInstance(instanceId, "delete");
        }
        historyService.deleteHistoricProcessInstance(instanceId);

        return true;
    }

    @Override
    public List<TaskDetail<?>> getTaskListByBusinessKey(String userId, String businessKey) {
        List<TaskDetail<?>> taskDetailList = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .taskAssignee(userId)
                .list();
        if (taskList != null) {
            for (Task task : taskList) {
                TaskDetail<?> taskDetail = getTaskDetail(task);
                taskDetailList.add(taskDetail);
            }
        }
        return taskDetailList;
    }

    @Override
    public List<String> getTaskIdListByBusinessKey(String userId, String businessKey) {
        List<String> taskIdList = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .taskAssignee(userId)
                .list();
        if (taskList != null) {
            for (Task task : taskList) {
                taskIdList.add(task.getId());
            }
        }
        return taskIdList;
    }

    @Override
    public ProcessDetail<?> getByBusinessKey(String businessKey) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        return getProcessDetail(historicProcessInstance);
    }

    @Override
    public ProcessDetail<?> getByInstanceId(String instanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        return getProcessDetail(historicProcessInstance);
    }

    @Override
    public ProcessDetail<?> getByTaskId(String taskId) {
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .singleResult();
        if (historicTaskInstance == null) {
            return null;
        }

        String instanceId = historicTaskInstance.getProcessInstanceId();

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        return getProcessDetail(historicProcessInstance);
    }

    @Override
    public TaskDetail<?> getTaskByTaskId(String taskId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return getTaskDetail(task);
    }

    @Override
    public List<String> getTaskHandleList(String taskId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return new ArrayList<>();
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        if (bpmnModel == null) {
            return new ArrayList<>();
        }
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        if (flowElements == null) {
            return new ArrayList<>();
        }
        FlowElement flowElement = getFlowElement(flowElements, task.getTaskDefinitionKey());
        if (flowElement == null) {
            return new ArrayList<>();
        }
        List<SequenceFlow> outgoingFlows = ((UserTask) flowElement).getOutgoingFlows();
        if (outgoingFlows == null) {
            return new ArrayList<>();
        }
        return getSequenceFlowOperation(flowElements, outgoingFlows);
    }

    private List<String> getSequenceFlowOperation(Collection<FlowElement> flowElements, List<SequenceFlow> outgoingFlows) {
        List<String> rList = new ArrayList<>();
        for (SequenceFlow sequenceFlow : outgoingFlows) {
            rList.addAll(analyzeOperation(sequenceFlow.getConditionExpression()));
            FlowElement flowElement = getFlowElement(flowElements, sequenceFlow.getTargetRef());
            if (flowElement instanceof ExclusiveGateway) {
                ExclusiveGateway exclusiveGateway = (ExclusiveGateway) flowElement;
                rList.addAll(getSequenceFlowOperation(flowElements, exclusiveGateway.getOutgoingFlows()));
            }
        }
        return rList;
    }

    private FlowElement getFlowElement(Collection<FlowElement> flowElements, String taskDefinitionKey) {
        for (FlowElement flowElement : flowElements) {
            if (flowElement.getId().equals(taskDefinitionKey)) {
                return flowElement;
            }
        }
        return null;
    }

    // 提取条件中的用户操作
    private List<String> analyzeOperation(String condition) {
        List<String> rList = new ArrayList<>();
        if (condition != null) {
            condition = condition.substring(2, condition.length() - 1);
            String[] conditionGroup = condition.split("\\|\\||&&");
            for (String group : conditionGroup) {
                String[] pd = group.split("==");
                if ("操作".equals(pd[0].trim())) {
                    rList.add(pd[1].trim().replaceAll("'", ""));
                }
            }
        }
        return rList;
    }

    @Override
    public ResponseEntity<byte[]> getImage(String definitionKey) {
        ResponseEntity<byte[]> entity = null;
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(definitionKey)
                    .latestVersion()
                    .singleResult();
            if (processDefinition == null) {
                return null;
            }

            String definitionId = processDefinition.getId();

            BpmnModel bpmnModel = repositoryService.getBpmnModel(definitionId);
            ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            InputStream is = processDiagramGenerator.generateDiagram(bpmnModel, "png", Collections.emptyList(), Collections.emptyList(), "宋体", "宋体", "宋体", null, 1.0);

            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/png");
            headers.add("Connection", "close");
            headers.add("Accept-Ranges", "bytes");
            entity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public ResponseEntity<byte[]> getRateImage(String businessKey) {
        ResponseEntity<byte[]> entity = null;
        try {
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceBusinessKey(businessKey)
                    .singleResult();
            if (historicProcessInstance == null) {
                return null;
            }

            String instanceId = historicProcessInstance.getId();
            String definitionId = historicProcessInstance.getProcessDefinitionId();

            BpmnModel bpmnModel = repositoryService.getBpmnModel(definitionId);

            List<String> highLightedActivities = new ArrayList<>();
            List<String> highLightedFlows;
            // 如果只用框选中当前流程执行任务
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(instanceId)
                    .list();
            for (Task task : taskList) {
                highLightedActivities.add(task.getTaskDefinitionKey());
            }

            // 当前流程所有活动
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId)
                    .orderByHistoricActivityInstanceId().asc()
                    .list();
            if (historicActivityInstanceList == null) {
                return null;
            }

//            // 如果用框选中全部历史任务
//            for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
//                highLightedActivities.add(historicActivityInstance.getActivityId());
//            }

            highLightedFlows = getHighLightedFlows(bpmnModel, historicActivityInstanceList);

            ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            InputStream is = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows, "宋体", "宋体", "宋体", null, 1.0);

            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/png");
            headers.add("Connection", "close");
            headers.add("Accept-Ranges", "bytes");
            entity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    /**
     * 获取需要高亮的线
     */
    private static List<String> getHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstanceList) {
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlows = new ArrayList<>();

        // 全部活动节点
        List<FlowElement> flowElementList = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            FlowElement flowElement = bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId());
            flowElementList.add(flowElement);
        }

        for (int i = 0; i < historicActivityInstanceList.size(); i++) {
            HistoricActivityInstance current = historicActivityInstanceList.get(i);
            String curActivityId = current.getActivityId();
            String curActivityType = current.getActivityType();
            if (current.getEndTime() != null) {
                FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(curActivityId);
                List<SequenceFlow> outgoingFlows = currentFlowNode.getOutgoingFlows();

                // 并行网关或兼容网关
                if ("parallelGateway".equals(curActivityType) || "inclusiveGateway".equals(curActivityType)) {
                    for (SequenceFlow outgoingFlow : outgoingFlows) {
                        FlowElement targetFlowElement = bpmnModel.getFlowElement(outgoingFlow.getTargetRef());
                        // 如果下级节点包含在所有历史节点中，则将当前节点的流出线高亮显示
                        if (flowElementList.contains(targetFlowElement)) {
                            highLightedFlows.add(outgoingFlow.getId());
                        }
                    }
                } else {
                    NEXT:
                    for (int j = i + 1; j < historicActivityInstanceList.size(); j++) {
                        HistoricActivityInstance next = historicActivityInstanceList.get(j);
                        for (SequenceFlow outgoingFlow : outgoingFlows) {
                            if (next.getActivityId().equals(outgoingFlow.getTargetRef())) {
                                highLightedFlows.add(outgoingFlow.getId());
                                break NEXT;
                            }
                        }
                    }
                }
            }
        }
        return highLightedFlows;
    }

    @Override
    public List<ApprovalLog> getApprovalLog(String businessKey) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance == null) {
            return new ArrayList<>();
        }

        String instanceId = historicProcessInstance.getId();

        List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(instanceId)
                .activityType("userTask")
                .orderByHistoricActivityInstanceStartTime().asc()
                .list();
        if (historicActivityInstanceList == null) {
            return new ArrayList<>();
        }

        List<ApprovalLog> approvalLogList = new ArrayList<>();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            String taskId = historicActivityInstance.getTaskId();
            HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                    .taskId(taskId)
                    .variableName("turnLog")
                    .singleResult();
            if (historicVariableInstance != null) {
                @SuppressWarnings("unchecked")
                List<TurnLog> turnLogList = (List<TurnLog>) historicVariableInstance.getValue();
                if (turnLogList != null) {
                    for (TurnLog turnLog : turnLogList) {
                        ApprovalLog approvalLog = new ApprovalLog();
                        approvalLog.setTaskDefinitionKey(turnLog.getTaskDefinitionKey());
                        approvalLog.setTaskName(turnLog.getTaskName());
                        approvalLog.setAssignee(turnLog.getAssignee());
                        approvalLog.setHandle(turnLog.getHandle());
                        approvalLog.setRemarks(Arrays.asList(turnLog.getRemark()));
                        approvalLog.setHandleTime(turnLog.getHandleTime());
                        approvalLogList.add(approvalLog);
                    }
                }
            }

            ApprovalLog approvalLog = new ApprovalLog();
            approvalLog.setTaskDefinitionKey(historicActivityInstance.getActivityId());
            approvalLog.setTaskName(historicActivityInstance.getActivityName());
            approvalLog.setAssignee(historicActivityInstance.getAssignee());
            approvalLog.setHandleTime(historicActivityInstance.getEndTime());

            historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                    .taskId(taskId)
                    .variableName("操作")
                    .singleResult();
            if (historicVariableInstance != null) {
                approvalLog.setHandle((String) historicVariableInstance.getValue());
            }

            List<Comment> commentList = taskService.getTaskComments(taskId);
            if (commentList != null) {
                List<String> remarks = new ArrayList<>();
                for (Comment comment : commentList) {
                    remarks.add(comment.getFullMessage());
                }
                approvalLog.setRemarks(remarks);
            }
            approvalLogList.add(approvalLog);
        }
        return approvalLogList;
    }

    @Override
    public Boolean turnTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskDefinitionKey(task.getTaskDefinitionKey());
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setHandleTime(new Date());
        turnLog.setHandle("转交");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.setAssignee(taskId, turn.getAssignee());
        return true;
    }

    @Override
    public Boolean delegateTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskDefinitionKey(task.getTaskDefinitionKey());
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setHandleTime(new Date());
        turnLog.setHandle("委派");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.delegateTask(taskId, turn.getAssignee());
        return true;
    }

    @Override
    public Boolean resolveTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            throw new BaseException("任务不存在");
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskDefinitionKey(task.getTaskDefinitionKey());
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setHandleTime(new Date());
        turnLog.setHandle("委派完成");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.resolveTask(taskId);
        return true;
    }

    @Override
    public Boolean suspend(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            throw new BaseException("流程不存在");
        }
        if (!processInstance.isSuspended()) {
            String instanceId = processInstance.getId();
            runtimeService.suspendProcessInstanceById(instanceId);
        }
        return true;
    }

    @Override
    public Boolean activate(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            throw new BaseException("流程不存在");
        }
        if (processInstance.isSuspended()) {
            String instanceId = processInstance.getId();
            runtimeService.activateProcessInstanceById(instanceId);
        }
        return true;
    }

    @Override
    public List<UserTaskInfo> getUserTaskList(String definitionKey) {
        List<UserTaskInfo> userTaskList = new ArrayList<>();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definitionKey)
                .latestVersion()
                .singleResult();
        String definitionId = processDefinition.getId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(definitionId);
        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask userTask = (UserTask) flowElement;
                if ("apply".equals(userTask.getId())) {
                    continue;
                }

                UserTaskInfo userTaskInfo = new UserTaskInfo();
                userTaskInfo.setTaskDefinitionKey(userTask.getId());
                userTaskInfo.setTaskName(userTask.getName());
                userTaskInfo.setAssignee(userTask.getAssignee());
                userTaskList.add(userTaskInfo);
            }
        }
        return userTaskList;
    }

    @Override
    public List<String> getBusinessKeyList(String userId) {
        List<String> businessKeyList = new ArrayList<>();
        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery()
                .involvedUser(userId)
                .list();
        if (historicProcessInstanceList != null) {
            for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
                businessKeyList.add(historicProcessInstance.getBusinessKey());
            }
        }
        return businessKeyList;
    }
}
