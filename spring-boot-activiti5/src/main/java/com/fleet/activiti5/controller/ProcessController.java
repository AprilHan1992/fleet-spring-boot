package com.fleet.activiti5.controller;

import com.fleet.activiti5.entity.*;
import com.fleet.activiti5.json.R;
import com.fleet.activiti5.page.Page;
import com.fleet.activiti5.page.PageUtil;
import com.fleet.activiti5.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Resource
    private ProcessService processService;

    /**
     * 我的待办列表
     */
    @PostMapping("/myTaskList/{userId}")
    public PageUtil<TaskDetail<?>> myTaskList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myTaskList(userId, page);
    }

    /**
     * 我的申请列表
     */
    @PostMapping("/myAppliedList/{userId}")
    public PageUtil<ProcessDetail<?>> myAppliedList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myAppliedList(userId, page);
    }

    /**
     * 我的审批列表
     */
    @PostMapping("/myApprovedList/{userId}")
    public PageUtil<ProcessDetail<?>> myApprovedList(@PathVariable("userId") String userId, @RequestBody Page page) {
        return processService.myApprovedList(userId, page);
    }

    /**
     * 获取同一类型流程数量
     */
    @GetMapping(value = "/getTotal")
    public R getTotal(@RequestParam String definitionKey) {
        Long total = processService.getTotal(definitionKey);
        return R.ok(total);
    }

    /**
     * 创建流程实例
     */
    @PostMapping("/start")
    public R start(@RequestBody ProcessDetail<?> processDetail) {
        return R.ok(processService.start(processDetail));
    }

    /**
     * 流程申请（创建流程实例并且自动完成 apply 填写申请单节点）
     */
    @PostMapping("/apply")
    public R apply(@RequestBody ProcessDetail<?> processDetail) {
        return R.ok(processService.apply(processDetail));
    }

    /**
     * 重新流程申请
     */
    @PostMapping("/reApply/{taskId}")
    public R reApply(@PathVariable("taskId") String taskId, @RequestBody ProcessDetail<?> processDetail) {
        return R.ok(processService.reApply(taskId, processDetail));
    }

    /**
     * 完成当前节点审批（并行网关在驳回后，用户重新提交会产生多条 task 任务）
     */
    @PostMapping("/completeTask")
    public R completeTask(@RequestBody Approval approval) {
        return R.ok(processService.completeTask(approval));
    }

    /**
     * 重新设置流程审批人
     */
    @PostMapping("/resetAssignees/{taskId}")
    public R resetAssignees(@PathVariable("taskId") String taskId, @RequestBody Map<String, Object> assignees) {
        return R.ok(processService.resetAssignees(taskId, assignees));
    }

    /**
     * 流程终止（只允许在申请“apply”节点终止流程）
     */
    @GetMapping("/stop")
    public R stop(@RequestParam String businessKey) {
        return R.ok(processService.stop(businessKey));
    }

    /**
     * 流程删除
     */
    @GetMapping("/delete")
    public R delete(@RequestParam String businessKey) {
        return R.ok(processService.delete(businessKey));
    }

    /**
     * 获取任务列表
     */
    @GetMapping(value = "/getTaskListByBusinessKey/{userId}")
    public R getTaskListByBusinessKey(@PathVariable("userId") String userId, @RequestParam String businessKey) {
        return R.ok(processService.getTaskListByBusinessKey(userId, businessKey));
    }

    /**
     * 获取任务id列表
     */
    @GetMapping(value = "/getTaskIdListByBusinessKey/{userId}")
    public R getTaskIdListByBusinessKey(@PathVariable("userId") String userId, @RequestParam String businessKey) {
        return R.ok(processService.getTaskIdListByBusinessKey(userId, businessKey));
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByBusinessKey")
    public R getByBusinessKey(@RequestParam String businessKey) {
        return R.ok(processService.getByBusinessKey(businessKey));
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByInstanceId")
    public R getByInstanceId(@RequestParam String instanceId) {
        return R.ok(processService.getByInstanceId(instanceId));
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByTaskId")
    public R getByTaskId(@RequestParam String taskId) {
        return R.ok(processService.getByTaskId(taskId));
    }

    /**
     * 获取流程节点操作
     */
    @GetMapping("/getTaskHandleList")
    public R getTaskHandleList(@RequestParam String taskId) {
        return R.ok(processService.getTaskHandleList(taskId));
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
    public R getApprovalLog(@RequestParam String businessKey) {
        return R.ok(processService.getApprovalLog(businessKey));
    }

    /**
     * 转交任务
     */
    @PostMapping(value = "/turnTask")
    public R turnTask(@RequestBody Turn turn) {
        return R.ok(processService.turnTask(turn));
    }

    /**
     * 委派任务
     */
    @PostMapping(value = "/delegateTask")
    public R delegateTask(@RequestBody Turn turn) {
        return R.ok(processService.delegateTask(turn));
    }

    /**
     * 委派人处理任务
     */
    @PostMapping(value = "/resolveTask")
    public R resolveTask(@RequestBody Turn turn) {
        return R.ok(processService.resolveTask(turn));
    }

    /**
     * 流程挂起
     */
    @GetMapping(value = "/suspendProcess")
    public R suspendProcess(@RequestParam String businessKey) {
        return R.ok(processService.suspendProcess(businessKey));
    }

    /**
     * 流程激活
     */
    @GetMapping(value = "/activateProcess")
    public R activateProcess(@RequestParam String businessKey) {
        return R.ok(processService.activateProcess(businessKey));
    }

    /**
     * 获取用户任务
     */
    @GetMapping("/getUserTaskList")
    List<UserTaskInfo> getUserTaskList(@RequestParam String definitionKey) {
        return processService.getUserTaskList(definitionKey);
    }
}
