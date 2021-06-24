package com.fleet.flowable.controller;

import com.fleet.flowable.entity.*;
import com.fleet.flowable.json.R;
import com.fleet.flowable.page.PageUtil;
import com.fleet.flowable.page.entity.Page;
import com.fleet.flowable.service.ProcessService;
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
    public R myTaskList(@PathVariable("userId") String userId,
                        @RequestParam(value = "initiator", required = false) String initiator,
                        @RequestParam(value = "title", required = false) String title,
                        @RequestParam(value = "definitionKey", required = false) String definitionKey,
                        @RequestParam(value = "definitionName", required = false) String definitionName,
                        @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                        @RequestParam(value = "pageRows", required = false, defaultValue = "20") int pageRows) {
        Page page = new Page(pageIndex, pageRows);
        PageUtil<TaskDetail<?>> pageUtil = processService.myTaskList(userId, initiator, title, definitionKey, definitionName, page);
        return R.ok(pageUtil);
    }

    /**
     * 我的申请列表
     */
    @PostMapping("/myAppliedList/{userId}")
    public R myAppliedList(@PathVariable("userId") String userId,
                           @RequestParam(value = "assignee", required = false) String assignee,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "definitionKey", required = false) String definitionKey,
                           @RequestParam(value = "definitionName", required = false) String definitionName,
                           @RequestParam(value = "state", required = false) String state,
                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                           @RequestParam(value = "pageRows", required = false, defaultValue = "20") int pageRows) {
        Page page = new Page(pageIndex, pageRows);
        PageUtil<ProcessDetail<?>> pageUtil = processService.myAppliedList(userId, assignee, title, definitionKey, definitionName, state, page);
        return R.ok(pageUtil);
    }

    /**
     * 我的审批列表
     */
    @PostMapping("/myApprovedList/{userId}")
    public R myApprovedList(@PathVariable("userId") String userId,
                            @RequestParam(value = "initiator", required = false) String initiator,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "definitionKey", required = false) String definitionKey,
                            @RequestParam(value = "definitionName", required = false) String definitionName,
                            @RequestParam(value = "state", required = false) String state,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(value = "pageRows", required = false, defaultValue = "20") int pageRows) {
        Page page = new Page(pageIndex, pageRows);
        PageUtil<ProcessDetail<?>> pageUtil = processService.myApprovedList(userId, initiator, title, definitionKey, definitionName, state, page);
        return R.ok(pageUtil);
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
     * 生成同一类型流程 businessKey，（需要注意，同时调用会有相同结果）
     */
    @GetMapping(value = "/generateBusinessKey")
    public R generateBusinessKey(@RequestParam String definitionKey) {
        String businessKey = processService.generateBusinessKey(definitionKey);
        return R.ok(businessKey);
    }

    /**
     * 创建流程实例
     */
    @PostMapping("/start")
    public R start(@RequestBody ProcessDetail<?> processDetail) {
        TaskDetail<?> task = processService.start(processDetail);
        return R.ok(task);
    }

    /**
     * 流程申请（创建流程实例并且自动完成 apply 填写申请单节点）
     */
    @PostMapping("/apply")
    public R apply(@RequestBody ProcessDetail<?> processDetail) {
        Boolean b = processService.apply(processDetail);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 重新流程申请
     */
    @PostMapping("/reapply/{taskId}")
    public R reapply(@PathVariable("taskId") String taskId, @RequestBody ProcessDetail<?> processDetail) {
        Boolean b = processService.reapply(taskId, processDetail);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 完成当前节点审批（并行网关在驳回后，用户重新提交会产生多条 task 任务）
     */
    @PostMapping("/completeTask")
    public R completeTask(@RequestBody Approval approval) {
        Boolean b = processService.completeTask(approval);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 重新设置流程审批人
     */
    @PostMapping("/resetAssignees/{taskId}")
    public R resetAssignees(@PathVariable("taskId") String taskId, @RequestBody Map<String, Object> assignees) {
        Boolean b = processService.resetAssignees(taskId, assignees);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 流程终止（只允许在申请“apply”节点终止流程）
     */
    @GetMapping("/stop")
    public R stop(@RequestParam String businessKey) {
        Boolean b = processService.stop(businessKey);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 流程删除
     */
    @GetMapping("/delete")
    public R delete(@RequestParam String businessKey) {
        Boolean b = processService.delete(businessKey);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 获取任务列表
     */
    @GetMapping(value = "/getTaskListByBusinessKey/{userId}")
    public R getTaskListByBusinessKey(@PathVariable("userId") String userId, @RequestParam String businessKey) {
        List<TaskDetail<?>> taskList = processService.getTaskListByBusinessKey(userId, businessKey);
        return R.ok(taskList);
    }

    /**
     * 获取任务id列表
     */
    @GetMapping(value = "/getTaskIdListByBusinessKey/{userId}")
    public R getTaskIdListByBusinessKey(@PathVariable("userId") String userId, @RequestParam String businessKey) {
        List<String> taskIdList = processService.getTaskIdListByBusinessKey(userId, businessKey);
        return R.ok(taskIdList);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByBusinessKey")
    public R getByBusinessKey(@RequestParam String businessKey) {
        ProcessDetail<?> process = processService.getByBusinessKey(businessKey);
        return R.ok(process);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByInstanceId")
    public R getByInstanceId(@RequestParam String instanceId) {
        ProcessDetail<?> process = processService.getByInstanceId(instanceId);
        return R.ok(process);
    }

    /**
     * 获取流程详情
     */
    @GetMapping(value = "/getByTaskId")
    public R getByTaskId(@RequestParam String taskId) {
        ProcessDetail<?> process = processService.getByTaskId(taskId);
        return R.ok(process);
    }

    /**
     * 获取任务详情
     */
    @GetMapping(value = "/getTaskByTaskId")
    public R getTaskByTaskId(@RequestParam String taskId) {
        TaskDetail<?> task = processService.getTaskByTaskId(taskId);
        return R.ok(task);
    }

    /**
     * 获取流程节点操作
     */
    @GetMapping("/getTaskHandleList")
    public R getTaskHandleList(@RequestParam String taskId) {
        List<String> taskHandleList = processService.getTaskHandleList(taskId);
        return R.ok(taskHandleList);
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
        List<ApprovalLog> approvalLog = processService.getApprovalLog(businessKey);
        return R.ok(approvalLog);
    }

    /**
     * 转交任务
     */
    @PostMapping(value = "/turnTask")
    public R turnTask(@RequestBody Turn turn) {
        Boolean b = processService.turnTask(turn);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 委派任务
     */
    @PostMapping(value = "/delegateTask")
    public R delegateTask(@RequestBody Turn turn) {
        Boolean b = processService.delegateTask(turn);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 委派人处理任务
     */
    @PostMapping(value = "/resolveTask")
    public R resolveTask(@RequestBody Turn turn) {
        Boolean b = processService.resolveTask(turn);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 流程挂起
     */
    @GetMapping(value = "/suspend")
    public R suspend(@RequestParam String businessKey) {
        Boolean b = processService.suspend(businessKey);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 流程激活
     */
    @GetMapping(value = "/activate")
    public R activate(@RequestParam String businessKey) {
        Boolean b = processService.activate(businessKey);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }

    /**
     * 获取流程用户任务
     */
    @GetMapping("/getUserTaskList")
    public R getUserTaskList(@RequestParam String definitionKey) {
        List<UserTaskInfo> userTaskList = processService.getUserTaskList(definitionKey);
        return R.ok(userTaskList);
    }

    /**
     * 获取用户参与流程 businessKey 列表
     */
    @GetMapping("/{userId}/getBusinessKeyList")
    public R getBusinessKeyList(@PathVariable("userId") String userId) {
        List<String> businessKeyList = processService.getBusinessKeyList(userId);
        return R.ok(businessKeyList);
    }
}
