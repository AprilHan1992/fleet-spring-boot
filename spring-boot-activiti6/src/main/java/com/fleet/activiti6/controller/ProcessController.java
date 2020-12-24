package com.fleet.activiti6.controller;

import com.fleet.activiti6.entity.*;
import com.fleet.activiti6.page.Page;
import com.fleet.activiti6.page.PageUtil;
import com.fleet.activiti6.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Resource
    private ProcessService processService;

    /**
     * 我的待办列表
     */
    @PostMapping("/myTaskList/{userId}")
    public PageUtil<TaskInfo<?>> myTaskList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myTaskList(userId, page);
    }

    /**
     * 我的待办列表（某一流程类型）
     */
    @PostMapping("/myTaskListByDefinitionKey/{userId}")
    public PageUtil<TaskInfo<?>> myTaskList(@PathVariable("userId") String userId, @RequestParam("definitionKey") String definitionKey, @RequestBody Page page) {
        return processService.myTaskListByDefinitionKey(userId, definitionKey, page);
    }

    /**
     * 我的申请列表
     */
    @PostMapping("/myAppliedList/{userId}")
    public PageUtil<ProcessInfo<?>> myAppliedList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myAppliedList(userId, page);
    }

    /**
     * 我的审批列表
     */
    @PostMapping("/myApprovedList/{userId}")
    public PageUtil<ProcessInfo<?>> myApprovedList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myApprovedList(userId, page);
    }

    /**
     * 获取同一类型流程数量
     */
    @GetMapping(value = "/getTotal")
    public long getTotal(@RequestParam String definitionKey) {
        return processService.getTotal(definitionKey);
    }

    /**
     * 创建流程实例
     */
    @PostMapping("/start")
    public TaskInfo<?> start(@RequestBody ProcessInfo<?> processInfo) {
        return processService.start(processInfo);
    }

    /**
     * 流程申请（创建流程实例并且自动完成 apply 填写申请单节点）
     */
    @PostMapping("/apply")
    public String apply(@RequestBody ProcessInfo<?> processInfo) {
        return processService.apply(processInfo);
    }

    /**
     * 重新流程申请
     */
    @PostMapping("/reApply/{taskId}")
    public String reApply(@PathVariable("taskId") String taskId, @RequestBody ProcessInfo<?> processInfo) {
        return processService.reApply(taskId, processInfo);
    }

    /**
     * 完成当前节点审批（并行网关在驳回后，用户重新提交会产生多条 task 任务）
     */
    @PostMapping("/completeTask")
    public String completeTask(@RequestBody Approval approval) {
        return processService.completeTask(approval);
    }

    /**
     * 重新设置流程审批人
     */
    @PostMapping("/resetAssignees/{businessKey}")
    public String resetAssignees(@PathVariable("businessKey") String businessKey, @RequestBody Map<String, String> assignees) {
        return processService.resetAssignees(businessKey, assignees);
    }

    /**
     * 流程中止
     */
    @GetMapping("/stop")
    public void stop(@RequestParam String businessKey) {
        processService.stop(businessKey);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByBusinessKey")
    public ProcessInfo<?> getByBusinessKey(@RequestParam String businessKey) {
        return processService.getByBusinessKey(businessKey);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByInstanceId")
    public ProcessInfo<?> getByInstanceId(@RequestParam String instanceId) {
        return processService.getByInstanceId(instanceId);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByTaskId")
    public ProcessInfo<?> getByTaskId(@RequestParam String taskId) {
        return processService.getByTaskId(taskId);
    }

    /**
     * 获取流程节点操作
     */
    @GetMapping("/getTaskOperation")
    public List<String> getTaskOperation(@RequestParam String taskId) {
        return processService.getTaskOperation(taskId);
    }

    /**
     * 流程图
     */
    @GetMapping("/processImage")
    public void processImage(@RequestParam String definitionKey, HttpServletResponse response) {
        ResponseEntity<byte[]> entity = processService.getImage(definitionKey);
        if (entity == null) {
            return;
        }
        try {
            byte[] bytes = entity.getBody();
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 流程图（附加进度）
     */
    @GetMapping("/processRateImage")
    public void processRateImage(@RequestParam String businessKey, HttpServletResponse response) {
        ResponseEntity<byte[]> entity = processService.getRateImage(businessKey);
        if (entity == null) {
            return;
        }
        try {
            byte[] bytes = entity.getBody();
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取流程审批记录
     */
    @GetMapping(value = "/getApprovalLog")
    public List<ApprovalLog> getApprovalLog(@RequestParam String businessKey) {
        return processService.getApprovalLog(businessKey);
    }

    /**
     * 转交任务
     */
    @PostMapping(value = "/turnTask")
    public String turnTask(@RequestBody Turn turn) {
        return processService.turnTask(turn);
    }

    /**
     * 委派任务
     */
    @PostMapping(value = "/delegateTask")
    public String delegateTask(@RequestBody Turn turn) {
        return processService.delegateTask(turn);
    }

    /**
     * 委派人处理任务
     */
    @PostMapping(value = "/resolveTask")
    public String resolveTask(@RequestBody Turn turn) {
        return processService.resolveTask(turn);
    }

    /**
     * 流程挂起
     */
    @GetMapping(value = "/suspendProcess")
    public String suspendProcess(@RequestParam String businessKey) {
        return processService.suspendProcess(businessKey);
    }

    /**
     * 流程激活
     */
    @GetMapping(value = "/activateProcess")
    public String activateProcess(@RequestParam String businessKey) {
        return processService.activateProcess(businessKey);
    }
}
