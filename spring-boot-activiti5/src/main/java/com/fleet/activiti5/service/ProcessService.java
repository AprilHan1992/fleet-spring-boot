package com.fleet.activiti5.service;

import com.fleet.activiti5.entity.*;
import com.fleet.activiti5.page.PageUtil;
import com.fleet.activiti5.page.entity.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface ProcessService {

    /**
     * 我的待办列表
     */
    PageUtil<TaskDetail<?>> myTaskList(String userId, String initiator, String title, String definitionKey, String definitionName, Page page);

    /**
     * 我的申请列表
     */
    PageUtil<ProcessDetail<?>> myAppliedList(String userId, String assignee, String title, String definitionKey, String definitionName, String state, Page page);

    /**
     * 我的审批列表
     */
    PageUtil<ProcessDetail<?>> myApprovedList(String userId, String initiator, String title, String definitionKey, String definitionName, String state, Page page);

    /**
     * 获取同一类型流程数量
     */
    Long getTotal(String definitionKey);

    /**
     * 生成同一类型流程 businessKey，（需要注意，同时调用会有相同结果）
     */
    String generateBusinessKey(String definitionKey);

    /**
     * 创建流程实例
     */
    TaskDetail<?> start(ProcessDetail<?> processDetail);

    /**
     * 流程申请（创建流程实例并且自动完成 apply 填写申请单节点）
     */
    Boolean apply(ProcessDetail<?> processDetail);

    /**
     * 重新流程申请
     */
    Boolean reapply(String taskId, ProcessDetail<?> processDetail);

    /**
     * 完成当前节点审批
     */
    Boolean completeTask(Approval approval);

    /**
     * 重新设置流程审批人
     */
    Boolean resetAssignees(String taskId, Map<String, Object> assignees);

    /**
     * 流程终止（只允许在申请“apply”节点终止流程）
     */
    Boolean stop(String businessKey);

    /**
     * 流程撤回（并行网关流程撤回，会产生多个）
     */
    Boolean withdraw(String businessKey);

    /**
     * 流程删除
     */
    Boolean delete(String businessKey);

    /**
     * 任务列表
     */
    List<TaskDetail<?>> getTaskListByBusinessKey(String userId, String businessKey);

    /**
     * 任务id列表
     */
    List<String> getTaskIdListByBusinessKey(String userId, String businessKey);

    /**
     * 流程详情
     */
    ProcessDetail<?> getByBusinessKey(String businessKey);

    /**
     * 流程详情
     */
    ProcessDetail<?> getByInstanceId(String instanceId);

    /**
     * 流程详情
     */
    ProcessDetail<?> getByTaskId(String taskId);

    /**
     * 任务详情
     */
    TaskDetail<?> getTaskByTaskId(String taskId);

    /**
     * 获取节点操作
     */
    List<String> getTaskHandleList(String taskId);

    /**
     * 流程图
     */
    ResponseEntity<byte[]> getImage(String definitionKey);

    /**
     * 流程图（附加进度）
     */
    ResponseEntity<byte[]> getRateImage(String businessKey);

    /**
     * 获取流程审批记录
     */
    List<ApprovalLog> getApprovalLog(String businessKey);

    /**
     * 转交任务
     */
    Boolean turnTask(Turn turn);

    /**
     * 委派任务
     */
    Boolean delegateTask(Turn turn);

    /**
     * 委派人处理任务
     */
    Boolean resolveTask(Turn turn);

    /**
     * 流程挂起
     */
    Boolean suspend(String businessKey);

    /**
     * 流程激活
     */
    Boolean activate(String businessKey);

    /**
     * 获取流程用户任务
     */
    List<UserTaskInfo> getUserTaskList(String definitionKey);

    /**
     * 获取用户参与流程 businessKey 列表
     */
    List<String> getBusinessKeyList(String userId);
}
