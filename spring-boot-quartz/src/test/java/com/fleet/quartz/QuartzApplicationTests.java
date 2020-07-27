package com.fleet.quartz;

import com.fleet.quartz.entity.QuartzJob;
import com.fleet.quartz.service.QuartzJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {

    @Resource
    private QuartzJobService quartzJobService;

    @Test
    public void insert() {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName("有参定时任务");
        quartzJob.setBeanName("testTask");
        quartzJob.setMethodName("run");
        quartzJob.setParam("参数");
        quartzJob.setCronExpression("*/20 * * * * ?");
        quartzJob.setEnabled(1);
        quartzJob.setRemark("有参定时任务");
        quartzJobService.insert(quartzJob);

        quartzJob = new QuartzJob();
        quartzJob.setJobName("无参定时任务");
        quartzJob.setBeanName("testTask");
        quartzJob.setMethodName("run");
        quartzJob.setCronExpression("*/20 * * * * ?");
        quartzJob.setEnabled(1);
        quartzJob.setRemark("无参定时任务");
        quartzJobService.insert(quartzJob);
    }

    @Test
    public void delete() {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(1);
        quartzJobService.delete(quartzJob);
    }

    @Test
    public void deletes() {
        quartzJobService.deletes(new Integer[]{1, 2, 3});
    }

    @Test
    public void update() {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(1);
        quartzJob.setJobName("测试");
        quartzJob.setBeanName("testTask");
        quartzJob.setMethodName("run");
        quartzJob.setParam("test");
        quartzJob.setCronExpression("*/20 * * * * ?");
        quartzJob.setEnabled(1);
        quartzJobService.update(quartzJob);
    }

    @Test
    public void get() {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(1);
        quartzJobService.get(quartzJob);
    }

    @Test
    public void list() {
        quartzJobService.list(new HashMap<>());
    }
}
