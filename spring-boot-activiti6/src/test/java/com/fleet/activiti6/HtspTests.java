package com.fleet.activiti6;

import com.alibaba.fastjson.JSON;
import com.fleet.activiti6.entity.*;
import com.fleet.activiti6.page.PageUtil;
import com.fleet.activiti6.page.entity.Page;
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
public class HtspTests {

    @Resource
    ProcessService processService;

    @Test
    public void myTaskList() {
        String userId = "1";
        Page page = new Page(1, 10);
        PageUtil<TaskDetail<?>> pageUtil = processService.myTaskList(userId, null, null, null, null, page);
        System.out.println(JSON.toJSONString(pageUtil));
    }

    @Test
    public void myAppliedList() {
        String userId = "1";
        Page page = new Page(1, 10);
        PageUtil<ProcessDetail<?>> pageUtil = processService.myAppliedList(userId, null, null, null, null, null, page);
        System.out.println(JSON.toJSONString(pageUtil));
    }

    @Test
    public void myApprovedList() {
        String userId = "1";
        Page page = new Page(1, 10);
        PageUtil<ProcessDetail<?>> pageUtil = processService.myApprovedList(userId, null, null, null, null, null, page);
        System.out.println(JSON.toJSONString(pageUtil));
    }

    @Test
    public void start() {
        ProcessDetail<List<Integer>> processDetail = new ProcessDetail<>();
        processDetail.setDefinitionKey("htsp");
        processDetail.setBusinessKey("htsp:1");
        processDetail.setTitle("合同流程一");
        processDetail.setInitiator("1");
        processDetail.setPhone("11111");
        processDetail.setEmail("1222");
        List<Integer> details = new ArrayList<>();
        details.add(1);
        details.add(3);
        processDetail.setDetails(details);
        processDetail.setRemark("这是测试");

        Map<String, Object> assignees = new HashMap<>();
        assignees.put("apply", "1");
        assignees.put("bmjl", "2");
        assignees.put("fw", "3");
        assignees.put("zjl", "4");
        processDetail.setAssignees(assignees);
        System.out.println(JSON.toJSONString(processService.start(processDetail)));
    }

    @Test
    public void apply() {
        ProcessDetail<List<Integer>> processDetail = new ProcessDetail<>();
        processDetail.setDefinitionKey("htsp");
        processDetail.setBusinessKey("htsp:1");
        processDetail.setTitle("合同流程一");
        processDetail.setInitiator("1");
        processDetail.setPhone("11111");
        processDetail.setEmail("1222");
        List<Integer> details = new ArrayList<>();
        details.add(1);
        details.add(3);
        processDetail.setDetails(details);
        processDetail.setRemark("这是测试");

        Map<String, Object> assignees = new HashMap<>();
        assignees.put("apply", "1");
        assignees.put("bmjl", "2");
        assignees.put("fw", "3");
        assignees.put("zjl", "4");
        processDetail.setAssignees(assignees);
        System.out.println(JSON.toJSONString(processService.apply(processDetail)));
    }

    @Test
    public void reapply() {
        ProcessDetail<List<Integer>> processDetail = new ProcessDetail<>();
        processDetail.setDefinitionKey("htsp");
        processDetail.setBusinessKey("htsp:1");
        processDetail.setTitle("合同流程一");
        processDetail.setInitiator("1");
        processDetail.setPhone("11111");
        processDetail.setEmail("1222");
        List<Integer> details = new ArrayList<>();
        details.add(1);
        details.add(3);
        processDetail.setDetails(details);
        processDetail.setRemark("这是测试");

        Map<String, Object> assignees = new HashMap<>();
        assignees.put("apply", "1");
        assignees.put("bmjl", "2");
        assignees.put("fw", "3");
        assignees.put("zjl", "4");
        processDetail.setAssignees(assignees);
        System.out.println(JSON.toJSONString(processService.reapply("2513", processDetail)));
    }

    @Test
    public void completeTask() {
        Approval approval = new Approval();

//        approval.setHandle("提交");
//        approval.setTaskId("34");
//        approval.setRemark("提交");

        approval.setHandle("同意");
        approval.setTaskId("74");
        approval.setRemark("同意");

//        approval.setHandle("驳回");
//        approval.setTaskId("2561");
//        approval.setRemark("驳回");

//        approval.setHandle("退回");
//        approval.setTaskId("5011");
//        approval.setRemark("退回");

//        approval.setHandle("结案");
//        approval.setTaskId("2508");
//        approval.setRemark("结案");

        System.out.println(JSON.toJSONString(processService.completeTask(approval)));
    }

    @Test
    public void resetAssignees() {
        Map<String, Object> assignees = new HashMap<>();
        assignees.put("bmjl", "4");
        processService.resetAssignees("43", assignees);
    }

    @Test
    public void stop() {
        processService.stop("htsp:1");
    }

    @Test
    public void delete() {
        processService.delete("htsp:1");
    }

    @Test
    public void getTaskListByBusinessKey() {
        List<TaskDetail<?>> taskList = processService.getTaskListByBusinessKey("1", "htsp:1");
        System.out.println(JSON.toJSONString(taskList));
    }

    @Test
    public void getTaskIdListByBusinessKey() {
        List<String> taskIdList = processService.getTaskIdListByBusinessKey("1", "htsp:1");
        System.out.println(JSON.toJSONString(taskIdList));
    }

    @Test
    public void getByBusinessKey() {
        ProcessDetail<?> processDetail = processService.getByBusinessKey("htsp:1");
        System.out.println(JSON.toJSONString(processDetail));
    }

    @Test
    public void getByInstanceId() {
        ProcessDetail<?> processDetail = processService.getByInstanceId("8");
        System.out.println(JSON.toJSONString(processDetail));
    }

    @Test
    public void getByTaskId() {
        ProcessDetail<?> processDetail = processService.getByTaskId("43");
        System.out.println(JSON.toJSONString(processDetail));
    }

    @Test
    public void getTaskHandleList() {
        List<String> taskHandleList = processService.getTaskHandleList("43");
        System.out.println(JSON.toJSONString(taskHandleList));
    }

    @Test
    public void getApprovalLog() {
        List<ApprovalLog> approvalLogList = processService.getApprovalLog("htsp:1");
        System.out.println(JSON.toJSONString(approvalLogList));
    }

    @Test
    public void turnTask() {
        Turn turn = new Turn();
        turn.setTaskId("43");
        turn.setAssignee("4");
        turn.setRemark("转交");
        processService.turnTask(turn);
    }

    @Test
    public void delegateTask() {
        Turn turn = new Turn();
        turn.setTaskId("43");
        turn.setAssignee("5");
        turn.setRemark("委派");
        processService.delegateTask(turn);
    }

    @Test
    public void resolveTask() {
        Turn turn = new Turn();
        turn.setTaskId("43");
        turn.setRemark("委派完成");
        processService.resolveTask(turn);
    }

    @Test
    public void suspend() {
        processService.suspend("htsp:1");
    }

    @Test
    public void activate() {
        processService.activate("htsp:1");
    }
}
