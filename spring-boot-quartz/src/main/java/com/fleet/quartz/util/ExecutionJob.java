package com.fleet.quartz.util;

import com.fleet.quartz.entity.QuartzJob;
import com.fleet.quartz.entity.QuartzJobLog;
import com.fleet.quartz.service.QuartzJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author April Han
 */
public class ExecutionJob extends QuartzJobBean {

    ThreadLocal<Long> times = new ThreadLocal<>();

    @Resource
    private QuartzJobLogService quartzJobLogService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        QuartzJob job = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_PARAM_KEY);
        QuartzJobLog jobLog = new QuartzJobLog();
        jobLog.setJobId(job.getId());
        jobLog.setJobName(job.getJobName());
        jobLog.setBeanName(job.getBeanName());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setParam(job.getParam());
        jobLog.setCronExpression(job.getCronExpression());
        jobLog.setCreateTime(new Date());

        times.set(System.currentTimeMillis());
        try {
            Class<?> clazz = SpringContextUtil.getBean(job.getBeanName()).getClass();
            if (StringUtils.isNotEmpty(job.getParam())) {
                Method method = clazz.getMethod(job.getMethodName(), String.class);
                method.invoke(clazz.newInstance(), job.getParam());
            } else {
                Method method = clazz.getMethod(job.getMethodName());
                method.invoke(clazz.newInstance());
            }
            jobLog.setState(1);
        } catch (Exception e) {
            jobLog.setState(0);
            jobLog.setError(e.getMessage());
        }
        long millis = System.currentTimeMillis() - times.get();
        times.remove();
        jobLog.setMillis(millis);
        quartzJobLogService.insert(jobLog);
    }
}
