package com.fleet.activiti6;

import com.alibaba.fastjson.JSON;
import com.fleet.activiti6.entity.*;
import com.fleet.activiti6.page.Page;
import com.fleet.activiti6.page.PageUtil;
import com.fleet.activiti6.service.ProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QjsqTests {

    @Resource
    ProcessService processService;

    @Test
    public void myTaskList() {
        String userId = "2";
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageRows(10);
        PageUtil<TaskInfo> pageUtil = processService.myTaskList(userId, page);
        System.out.println(JSON.toJSONString(pageUtil));
    }

    @Test
    public void myAppliedList() {
        String userId = "1";
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageRows(10);
        processService.myAppliedList(userId, page);
    }

    @Test
    public void myApprovedList() {
        String userId = "1";
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageRows(10);
        processService.myApprovedList(userId, page);
    }

    @Test
    public void start() {
        ProcessInfo<List<Integer>> processInfo = new ProcessInfo<>();
        processInfo.setDefinitionKey("qjsq");
        processInfo.setBusinessKey("qjsq:1");
        processInfo.setTitle("这是请假流程");
        processInfo.setInitiator("1");
        processInfo.setPhone("11111");
        processInfo.setEmail("1222");
        List<Integer> details = new ArrayList<>();
        details.add(1);
        details.add(3);
        processInfo.setDetails(details);
        processInfo.setRemark("这是测试");

        Map<String, String> assignees = new HashMap<>();
        assignees.put("填写申请单", "1");
        assignees.put("经理审批", "2");
        assignees.put("人事记录", "3");
        processInfo.setAssignees(assignees);
        processService.start(processInfo);
    }

    @Test
    public void apply() {
        ProcessInfo<List<Integer>> processInfo = new ProcessInfo<>();
        processInfo.setDefinitionKey("qjsq");
        processInfo.setBusinessKey("qjsq:1");
        processInfo.setTitle("这是请假流程");
        processInfo.setInitiator("1");
        processInfo.setPhone("11111");
        processInfo.setEmail("1222");
        List<Integer> details = new ArrayList<>();
        details.add(1);
        details.add(3);
        processInfo.setDetails(details);
        processInfo.setRemark("这是测试");

        Map<String, String> assignees = new HashMap<>();
        assignees.put("填写申请单", "1");
        assignees.put("经理审批", "2");
        assignees.put("人事记录", "3");
        processInfo.setAssignees(assignees);
        processService.apply(processInfo);
    }

    @Test
    public void completeTask() {
//        Approval approval = new Approval();
//        approval.setFlag("提交");
//        approval.setTaskId("7510");
//        approval.setRemark("这是提交");
//        processService.completeTask(approval);

//         Approval approval = new Approval();
//         approval.setFlag("重新提交");
//         approval.setTaskId("5007");
//         approval.setRemark("重新提交");
//         processService.completeTask(approval);

//        Approval approval = new Approval();
//        approval.setFlag("驳回");
//        approval.setTaskId("2519");
//        approval.setRemark("驳回");
//        processService.completeTask(approval);

        Approval approval = new Approval();
        approval.setFlag("同意");
        approval.setTaskId("7506");
        approval.setRemark("同意");
        processService.completeTask(approval);
    }

    @Test
    public void stop() {
        processService.stop("qjsq:1");
    }

    @Test
    public void getByBusinessKey() {
        ProcessInfo<?> processInfo = processService.getByBusinessKey("qjsq:1");
        System.out.println(JSON.toJSONString(processInfo));
    }

    @Test
    public void getByInstanceId() {
        ProcessInfo<?> processInfo = processService.getByInstanceId("5007");
        System.out.println(JSON.toJSONString(processInfo));
    }

    @Test
    public void getByTaskId() {
        ProcessInfo<?> processInfo = processService.getByTaskId("23");
        System.out.println(JSON.toJSONString(processInfo));
    }

    @Test
    public void getTaskOperation() {
        List<String> taskOperationList = processService.getTaskOperation("23");
        System.out.println(JSON.toJSONString(taskOperationList));
    }

    @Test
    public void getApprovalLog() {
        List<ApprovalLog> approvalLogList = processService.getApprovalLog("qjsq:1");
        System.out.println(JSON.toJSONString(approvalLogList));
    }

    @Test
    public void turnTask() {
        Turn turn = new Turn();
        turn.setTaskId("23");
        turn.setAssignee("2");
        turn.setRemark("转交");
        processService.turnTask(turn);
    }

    @Test
    public void delegateTask() {
        Turn turn = new Turn();
        turn.setTaskId("17507");
        turn.setAssignee("2");
        turn.setRemark("委派");
        processService.delegateTask(turn);
    }

    @Test
    public void resolveTask() {
        Turn turn = new Turn();
        turn.setTaskId("17507");
        turn.setRemark("委派完成");
        processService.resolveTask(turn);
    }

    @Test
    public void suspendProcess() {
        processService.suspendProcess("qjsq:1");
    }

    @Test
    public void activateProcess() {
        processService.activateProcess("qjsq:1");
    }
}
