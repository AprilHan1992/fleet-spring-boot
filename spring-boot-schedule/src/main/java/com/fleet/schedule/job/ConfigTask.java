package com.fleet.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * 基于接口 SchedulingConfigurer 的动态定时任务
 *
 * @author April Han
 */
@Component
public class ConfigTask implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTask.class);

    /**
     * cron 表达式
     */
    private String cronExpression = "*/5 * * * * ?";

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addCronTask(
                // 定时任务要执行的内容
                () -> logger.info("SchedulingConfigurer 定时任务1执行时间：" + System.currentTimeMillis()),
                // cron 表达式
                getCronExpression()
        );
        taskRegistrar.addTriggerTask(
                // 定时任务要执行的内容
                () -> logger.info("SchedulingConfigurer 定时任务2执行时间：" + System.currentTimeMillis()),
                // 定时任务触发，可修改定时任务的执行周期
                triggerContext -> new CronTrigger(getCronExpression()).nextExecutionTime(triggerContext)
        );
    }
}
