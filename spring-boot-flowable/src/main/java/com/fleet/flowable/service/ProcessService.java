package com.fleet.flowable.service;

import com.fleet.flowable.entity.*;
import com.fleet.flowable.page.Page;
import com.fleet.flowable.page.PageUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProcessService {

    /**
     * 我的待办列表
     */
    PageUtil<TaskInfo<?>> myTaskList(String userId, Page page);

    /**
     * 我的待办列表（某一流程类型）
     */
    PageUtil<TaskInfo<?>> myTaskListByDefinitionKey(String userId, String definitionKey, Page page);

    /**
     * 我的申请列表
     */
    PageUtil<ProcessInfo<?>> myAppliedList(String userId, Page page);

    /**
     * 我的审批列表
     */
    PageUtil<ProcessInfo<?>> myApprovedList(String userId, Page page);

    /**
     * 获取同一类型流程数量
     */
    Long getTotal(String definitionKey);

    /**
     * 创建流程实例
     */
    TaskInfo<?> start(ProcessInfo<?> processInfo);

    /**
     * 流程申请（创建流程实例并且自动完成 apply 填写申请单节点）
     */
    String apply(ProcessInfo<?> processInfo);

    /**
     * 重新流程申请
     */
    String reApply(String taskId, ProcessInfo<?> processInfo);

    /**
     * 完成当前节点审批
     */
    String completeTask(Approval approval);

    /**
     * 重新设置流程审批人
     */
    String resetAssignees(String businessKey, Map<String, String> assignees);

    /**
     * 流程中止
     */
    String stop(String businessKey);

    /**
     * 流程详情
     */
    ProcessInfo<?> getByBusinessKey(String businessKey);

    /**
     * 流程详情
     */
    ProcessInfo<?> getByInstanceId(String instanceId);

    /**
     * 流程详情
     */
    ProcessInfo<?> getByTaskId(String taskId);

    /**
     * 获取节点操作
     */
    List<String> getTaskOperation(String taskId);

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
    String turnTask(Turn turn);

    /**
     * 委派任务
     */
    String delegateTask(Turn turn);

    /**
     * 委派人处理任务
     */
    String resolveTask(Turn turn);

    /**
     * 流程挂起
     */
    String suspendProcess(String businessKey);

    /**
     * 流程激活
     */
    String activateProcess(String businessKey);
}
