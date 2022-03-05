package com.fleet.schedule.controller;

import com.fleet.schedule.job.ConfigTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private ConfigTask configTask;

    @RequestMapping("/cronExpression")
    public void cronExpression() {
        configTask.setCronExpression("*/10 * * * * ?");
    }
}
