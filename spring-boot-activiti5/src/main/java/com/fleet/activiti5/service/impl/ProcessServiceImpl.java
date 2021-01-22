package com.fleet.activiti5.service.impl;

import com.fleet.activiti5.entity.*;
import com.fleet.activiti5.page.Page;
import com.fleet.activiti5.page.PageUtil;
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
    public PageUtil<TaskInfo> myTaskList(String userId, Page page) {
        PageUtil<TaskInfo> pageUtil = new PageUtil<>();
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee(userId);
        String title = Objects.toString(page.get("title"), "");
        if (StringUtils.isNotEmpty(title)) {
            taskQuery.processVariableValueLike("title", "%" + title + "%");
        }
        String definitionKey = Objects.toString(page.get("definitionKey"), "");
        if (StringUtils.isNotEmpty(definitionKey)) {
            taskQuery.processDefinitionKey(definitionKey);
        }
        String definitionName = Objects.toString(page.get("definitionName"), "");
        if (StringUtils.isNotEmpty(definitionName)) {
            taskQuery.processDefinitionName(definitionName);
        }
        taskQuery.orderByTaskCreateTime().asc();
        List<Task> taskList = taskQuery.listPage(page.getFromPageIndex(), page.getPageRows());

        List<TaskInfo> taskInfoList = new ArrayList<>();
        if (taskList != null) {
            for (Task task : taskList) {
                TaskInfo taskInfo = getTaskInfo(task);
                taskInfoList.add(taskInfo);
            }
        }

        long count = taskQuery.count();

        pageUtil.setList(taskInfoList);
        page.setTotalRows((int) count);
        pageUtil.setPage(page);
        return pageUtil;
    }

    private TaskInfo getTaskInfo(Task task) {
        String taskId = task.getId();
        String instanceId = task.getProcessInstanceId();

        String initiator = (String) taskService.getVariable(taskId, "initiator");
        ProcessInfo<?> processInfo = (ProcessInfo<?>) taskService.getVariable(taskId, "info");

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (processInstance == null) {
            return null;
        }

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        // 将 task 转换成我们需要的格式 taskInfo
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setInstanceId(instanceId);
        taskInfo.setDefinitionKey(processInfo.getDefinitionKey());
        taskInfo.setBusinessKey(processInstance.getBusinessKey());
        taskInfo.setTitle(processInfo.getTitle());
        taskInfo.setInitiator(initiator);
        taskInfo.setPhone(processInfo.getPhone());
        taskInfo.setEmail(processInfo.getEmail());
        taskInfo.setStartTime(historicProcessInstance.getStartTime());
        taskInfo.setRemark(processInfo.getRemark());
        taskInfo.setTaskId(taskId);
        taskInfo.setTaskName(task.getName());
        taskInfo.setCreateTime(task.getCreateTime());
        taskInfo.setAssignee(task.getAssignee());
        return taskInfo;
    }

    @Override
    public PageUtil<ProcessInfo<?>> myAppliedList(String userId, Page page) {
        PageUtil<ProcessInfo<?>> pageUtil = new PageUtil<>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.variableValueEquals("initiator", userId);
        String title = Objects.toString(page.get("title"), "");
        if (StringUtils.isNotEmpty(title)) {
            historicProcessInstanceQuery.variableValueLike("title", "%" + title + "%");
        }
        String definitionKey = Objects.toString(page.get("definitionKey"), "");
        if (StringUtils.isNotEmpty(definitionKey)) {
            historicProcessInstanceQuery.processDefinitionKey(definitionKey);
        }
        String definitionName = Objects.toString(page.get("definitionName"), "");
        if (StringUtils.isNotEmpty(definitionName)) {
            historicProcessInstanceQuery.processDefinitionName(definitionName);
        }
        historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc();
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.listPage(page.getFromPageIndex(), page.getPageRows());

        List<ProcessInfo<?>> processInfoList = new ArrayList<>();
        if (historicProcessInstanceList != null) {
            for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
                ProcessInfo<?> processInfo = getProcessInfo(historicProcessInstance);
                processInfoList.add(processInfo);
            }
        }

        long count = historicProcessInstanceQuery.count();

        pageUtil.setList(processInfoList);
        page.setTotalRows((int) count);
        pageUtil.setPage(page);
        return pageUtil;
    }

    @Override
    public PageUtil<ProcessInfo<?>> myApprovedList(String userId, Page page) {
        PageUtil<ProcessInfo<?>> pageUtil = new PageUtil<>();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.involvedUser(userId);
        String title = Objects.toString(page.get("title"), "");
        if (StringUtils.isNotEmpty(title)) {
            historicProcessInstanceQuery.variableValueLike("title", "%" + title + "%");
        }
        String definitionKey = Objects.toString(page.get("definitionKey"), "");
        if (StringUtils.isNotEmpty(definitionKey)) {
            historicProcessInstanceQuery.processDefinitionKey(definitionKey);
        }
        String definitionName = Objects.toString(page.get("definitionName"), "");
        if (StringUtils.isNotEmpty(definitionName)) {
            historicProcessInstanceQuery.processDefinitionName(definitionName);
        }
        historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc();
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.listPage(page.getFromPageIndex(), page.getPageRows());

        List<ProcessInfo<?>> processInfoList = new ArrayList<>();
        if (historicProcessInstanceList != null) {
            for (HistoricProcessInstance historicProcessInstance : historicProcessInstanceList) {
                ProcessInfo<?> processInfo = getProcessInfo(historicProcessInstance);
                processInfoList.add(processInfo);
            }
        }

        long count = historicProcessInstanceQuery.count();

        pageUtil.setList(processInfoList);
        page.setTotalRows((int) count);
        pageUtil.setPage(page);
        return pageUtil;
    }

    private ProcessInfo<?> getProcessInfo(HistoricProcessInstance historicProcessInstance) {
        String instanceId = historicProcessInstance.getId();

        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(instanceId)
                .variableName("info")
                .singleResult();
        if (historicVariableInstance == null) {
            return null;
        }

        ProcessInfo<?> processInfo = (ProcessInfo<?>) historicVariableInstance.getValue();

        if (historicProcessInstance.getEndTime() == null) {
            processInfo.setState(1);
            Task task = taskService.createTaskQuery()
                    .processInstanceId(instanceId)
                    .singleResult();
            if (task != null) {
                processInfo.setAssignee(task.getAssignee());
            }
        } else {
            // 查询流程是否终止
            HistoricVariableInstance terminated = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(instanceId)
                    .variableName("terminated")
                    .singleResult();
            if (terminated != null) {
                processInfo.setState(3);
            } else {
                processInfo.setState(2);
            }
        }

        processInfo.setInstanceId(instanceId);
        processInfo.setStartTime(historicProcessInstance.getStartTime());
        processInfo.setEndTime(historicProcessInstance.getEndTime());
        return processInfo;
    }

    @Override
    public Long getTotal(String definitionKey) {
        return historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(definitionKey)
                .count();
    }

    @Override
    public TaskInfo start(ProcessInfo<?> processInfo) {
        // 先判断business的唯一性
        String businessKey = processInfo.getBusinessKey();
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance != null) {
            return null;
        }

        identityService.setAuthenticatedUserId(processInfo.getInitiator());

        Map<String, Object> variables = new HashMap<>();
        variables.put("definitionKey", processInfo.getDefinitionKey());
        variables.put("businessKey", processInfo.getBusinessKey());
        variables.put("title", processInfo.getTitle());
        variables.put("initiator", processInfo.getInitiator());
        variables.put("info", processInfo);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInfo.getDefinitionKey(), processInfo.getBusinessKey(), variables);
        if (processInstance == null) {
            return null;
        }

        String instanceId = processInstance.getId();
        Task task = taskService.createTaskQuery()
                .processInstanceId(instanceId)
                .singleResult();
        return getTaskInfo(task);
    }

    @Override
    public String apply(ProcessInfo<?> processInfo) {
        TaskInfo taskInfo = start(processInfo);
        if (taskInfo == null) {
            return "失败";
        }

        Approval approval = new Approval();
        approval.setTaskId(taskInfo.getTaskId());
        approval.setFlag("提交");
        approval.setRemark("提交");
        completeTask(approval);
        return "成功";
    }

    @Override
    public String reApply(String taskId, ProcessInfo<?> processInfo) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        String instanceId = task.getProcessInstanceId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("操作", "重新提交");
        taskService.setVariablesLocal(taskId, variables);
        taskService.addComment(taskId, instanceId, "重新提交");
        variables.put("info", processInfo);
        taskService.complete(taskId, variables);
        return "成功";
    }

    @Override
    public String completeTask(Approval approval) {
        String taskId = approval.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        String instanceId = task.getProcessInstanceId();

        List<String> taskOperationList = getTaskOperation(taskId);
        if (!taskOperationList.contains(approval.getFlag())) {
            return "任务节点没有“" + approval.getFlag() + "”操作";
        }

        Map<String, Object> variables = new HashMap<>();
        variables.put("操作", approval.getFlag());
        taskService.setVariablesLocal(taskId, variables);
        taskService.addComment(taskId, instanceId, approval.getRemark());
        taskService.complete(taskId, variables);
        return "成功";
    }

    @Override
    public String resetAssignees(String businessKey, Map<String, String> assignees) {
        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        String taskId = task.getId();

        ProcessInfo<?> processInfo = (ProcessInfo<?>) taskService.getVariable(taskId, "info");
        for (String key : assignees.keySet()) {
            processInfo.getAssignees().put(key, assignees.get(key));
        }

        Map<String, Object> variables = new HashMap<>();
        variables.put("info", processInfo);
        taskService.setVariables(taskId, variables);

        if (task.getName() != null) {
            if (assignees.containsKey(task.getName())) {
                if (assignees.get(task.getName()) != null && !assignees.get(task.getName()).equals(task.getAssignee())) {
                    Turn turn = new Turn();
                    turn.setTaskId(taskId);
                    turn.setAssignee(assignees.get(task.getName()));
                    turn.setRemark("修改审批人后转交给新的审批人");
                    turnTask(turn);
                }
            }
        }
        return "成功";
    }

    @Override
    public String stop(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            return "businessKey:" + businessKey + "不存在或流程已结案，无法终止";
        }

        String definitionId = processInstance.getProcessDefinitionId();

        ProcessDefinitionEntity processDefinitionEntity = getProcessDefinitionEntity(definitionId);
        ActivityImpl endActivity = getEndActivityImpl(processDefinitionEntity);
        if (endActivity == null) {
            return "无结束事件";
        }

        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .list();
        if (taskList == null || taskList.size() == 0) {
            return "任务不存在";
        } else if (taskList.size() > 1) {
            return "只允许在申请节点终止流程";
        } else {
            Task task = taskList.get(0);
            String taskId = task.getId();
            String instanceId = task.getProcessInstanceId();
            String activityId = task.getTaskDefinitionKey();

            if (!"apply".equals(activityId)) {
                return "只允许在申请节点终止流程";
            }

            ActivityImpl curActivity = getActivityImpl(processDefinitionEntity, activityId);
            curActivity.getOutgoingTransitions().clear();
            // 创建新流向
            TransitionImpl transition = curActivity.createOutgoingTransition();
            transition.setDestination(endActivity);

            Map<String, Object> variables = new HashMap<>();
            variables.put("操作", "终止");
            taskService.setVariablesLocal(taskId, variables);
            taskService.addComment(taskId, instanceId, "终止");
            variables.put("terminated", "true");
            taskService.complete(taskId, variables);
        }

        return "成功";
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
    public String delete(String businessKey) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance == null) {
            return "businessKey:" + businessKey + "不存在";
        }

        String instanceId = historicProcessInstance.getId();
        // 流程未结束
        if (historicProcessInstance.getEndTime() == null) {
            runtimeService.deleteProcessInstance(instanceId, "delete");
        }
        historyService.deleteHistoricProcessInstance(instanceId);

        return "成功";
    }

    @Override
    public ProcessInfo<?> getByBusinessKey(String businessKey) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (historicProcessInstance == null) {
            return null;
        }

        String instanceId = historicProcessInstance.getId();

        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(instanceId)
                .variableName("info")
                .singleResult();
        if (historicVariableInstance == null) {
            return null;
        }
        return (ProcessInfo<?>) historicVariableInstance.getValue();
    }

    @Override
    public ProcessInfo<?> getByInstanceId(String instanceId) {
        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(instanceId)
                .variableName("info")
                .singleResult();
        if (historicVariableInstance == null) {
            return null;
        }
        return (ProcessInfo<?>) historicVariableInstance.getValue();
    }

    @Override
    public ProcessInfo<?> getByTaskId(String taskId) {
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .taskId(taskId)
                .singleResult();
        if (historicTaskInstance == null) {
            return null;
        }

        String instanceId = historicTaskInstance.getProcessInstanceId();
        HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(instanceId)
                .variableName("info")
                .singleResult();
        if (historicVariableInstance == null) {
            return null;
        }
        return (ProcessInfo<?>) historicVariableInstance.getValue();
    }

    @Override
    public List<String> getTaskOperation(String taskId) {
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
            String currentActivityId = current.getActivityId();
            String currentActivityType = current.getActivityType();
            if (current.getEndTime() != null) {
                FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityId);
                List<SequenceFlow> outgoingFlows = currentFlowNode.getOutgoingFlows();

                // 并行网关或兼容网关
                if ("parallelGateway".equals(currentActivityType) || "inclusiveGateway".equals(currentActivityType)) {
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
                        approvalLog.setTaskName(turnLog.getTaskName());
                        approvalLog.setAssignee(turnLog.getAssignee());
                        approvalLog.setFlag(turnLog.getFlag());
                        approvalLog.setRemark(turnLog.getRemark());
                        approvalLog.setOperTime(turnLog.getOperTime());
                        approvalLogList.add(approvalLog);
                    }
                }
            }

            ApprovalLog approvalLog = new ApprovalLog();
            approvalLog.setTaskName(historicActivityInstance.getActivityName());
            approvalLog.setAssignee(historicActivityInstance.getAssignee());
            approvalLog.setOperTime(historicActivityInstance.getEndTime());

            historicVariableInstance = historyService.createHistoricVariableInstanceQuery()
                    .taskId(taskId)
                    .variableName("操作")
                    .singleResult();
            if (historicVariableInstance != null) {
                approvalLog.setFlag((String) historicVariableInstance.getValue());
            }

            Comment comment = taskService.getComment(taskId);
            if (comment != null) {
                approvalLog.setRemark(comment.getFullMessage());
            }
            approvalLogList.add(approvalLog);
        }
        return approvalLogList;
    }

    @Override
    public String turnTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setOperTime(new Date());
        turnLog.setFlag("转交");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.setAssignee(taskId, turn.getAssignee());
        return "成功";
    }

    @Override
    public String delegateTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setOperTime(new Date());
        turnLog.setFlag("委派");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.delegateTask(taskId, turn.getAssignee());
        return "成功";
    }

    @Override
    public String resolveTask(Turn turn) {
        String taskId = turn.getTaskId();
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        if (task == null) {
            return "任务不存在";
        }

        @SuppressWarnings("unchecked")
        List<TurnLog> turnLogList = (List<TurnLog>) taskService.getVariableLocal(taskId, "turnLog");
        if (turnLogList == null) {
            turnLogList = new ArrayList<>();
        }
        TurnLog turnLog = new TurnLog();
        turnLog.setTaskName(task.getName());
        turnLog.setAssignee(task.getAssignee());
        turnLog.setOperTime(new Date());
        turnLog.setFlag("委派完成");
        turnLog.setRemark(turn.getRemark());
        turnLogList.add(turnLog);
        taskService.setVariableLocal(taskId, "turnLog", turnLogList);

        taskService.resolveTask(taskId);
        return "成功";
    }

    @Override
    public String suspendProcess(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            return "流程不存在";
        }
        if (processInstance.isSuspended()) {
            return "已挂起";
        }

        String instanceId = processInstance.getId();
        runtimeService.suspendProcessInstanceById(instanceId);
        return "成功";
    }

    @Override
    public String activateProcess(String businessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance == null) {
            return "流程不存在";
        }
        if (!processInstance.isSuspended()) {
            return "已激活";
        }

        String instanceId = processInstance.getId();
        runtimeService.activateProcessInstanceById(instanceId);
        return "成功";
    }
}
