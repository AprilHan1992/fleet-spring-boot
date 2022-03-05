package com.fleet.quartz.controller;

import com.fleet.quartz.entity.QuartzJobLog;
import com.fleet.quartz.service.QuartzJobLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 定时任务记录
 *
 * @author April Han
 */
@RestController
@RequestMapping("/quartz/jobLog")
public class QuartzJobLogController {

    @Resource
    private QuartzJobLogService quartzJobLogService;

    @PostMapping("/insert")
    public void insert(@RequestBody QuartzJobLog quartzJobLog) {
        quartzJobLogService.insert(quartzJobLog);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody QuartzJobLog quartzJobLog) {
        quartzJobLogService.delete(quartzJobLog);
    }

    @RequestMapping(value = "/deletes", method = {RequestMethod.GET, RequestMethod.POST})
    public void deletes(@RequestParam Integer[] ids) {
        quartzJobLogService.deletes(ids);
    }

    @PostMapping("/update")
    public void update(@RequestBody QuartzJobLog quartzJobLog) {
        quartzJobLogService.update(quartzJobLog);
    }

    @GetMapping("/get")
    public QuartzJobLog get(@RequestParam("id") Integer id) {
        QuartzJobLog quartzJobLog = new QuartzJobLog();
        quartzJobLog.setId(id);
        return get(quartzJobLog);
    }

    @PostMapping("/get")
    public QuartzJobLog get(QuartzJobLog quartzJobLog) {
        return quartzJobLogService.get(quartzJobLog);
    }

    @GetMapping("/list")
    public List<QuartzJobLog> list(@RequestParam Map<String, Object> map) {
        return quartzJobLogService.list(map);
    }
}
