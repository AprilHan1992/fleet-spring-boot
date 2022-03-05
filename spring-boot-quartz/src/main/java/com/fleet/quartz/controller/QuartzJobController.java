package com.fleet.quartz.controller;

import com.fleet.quartz.entity.QuartzJob;
import com.fleet.quartz.service.QuartzJobService;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author April Han
 */
@RestController
@RequestMapping("/quartz/job")
public class QuartzJobController {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobController.class);

    @Resource
    private QuartzJobService quartzJobService;

    @PostMapping("/insert")
    public void insert(@RequestBody QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            logger.error("cron表达式：" + quartzJob.getCronExpression() + "错误");
            return;
        }
        quartzJobService.insert(quartzJob);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody QuartzJob quartzJob) {
        quartzJobService.delete(quartzJob);
    }

    @RequestMapping(value = "/deletes", method = {RequestMethod.GET, RequestMethod.POST})
    public void deletes(@RequestParam Integer[] ids) {
        quartzJobService.deletes(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody QuartzJob quartzJob) {
        if (!CronExpression.isValidExpression(quartzJob.getCronExpression())) {
            logger.error("cron表达式：" + quartzJob.getCronExpression() + "错误");
            return;
        }
        quartzJobService.update(quartzJob);
    }

    @GetMapping("/get")
    public QuartzJob get(@RequestParam("id") Integer id) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        return get(quartzJob);
    }

    @PostMapping("/get")
    public QuartzJob get(QuartzJob quartzJob) {
        return quartzJobService.get(quartzJob);
    }

    @GetMapping("/list")
    public List<QuartzJob> list(@RequestParam Map<String, Object> map) {
        return quartzJobService.list(map);
    }

    /**
     * 启动
     *
     * @param id
     * @return
     */
    @GetMapping("/run/{id}")
    public void run(@PathVariable("id") Integer id) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        quartzJob = quartzJobService.get(quartzJob);
        if (quartzJob == null) {
            logger.error("定时任务不存在");
            return;
        }
        quartzJobService.run(id);
    }

    /**
     * 暂停
     *
     * @param id
     * @return
     */
    @GetMapping("/pause/{id}")
    public void pause(@PathVariable("id") Integer id) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        quartzJob = quartzJobService.get(quartzJob);
        if (quartzJob == null) {
            logger.error("定时任务不存在");
            return;
        }
        quartzJobService.pause(id);
    }

    /**
     * 重启
     *
     * @param id
     * @return
     */
    @GetMapping("/resume/{id}")
    public void resume(@PathVariable("id") Integer id) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        quartzJob = quartzJobService.get(quartzJob);
        if (quartzJob == null) {
            logger.error("定时任务不存在");
            return;
        }
        quartzJobService.resume(id);
    }
}
