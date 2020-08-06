package com.fleet.xxl.job.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author April Han
 */
@Component
public class XxlJobTask {

    private static final Logger logger = LoggerFactory.getLogger(XxlJobTask.class);

    @XxlJob("jobHandler")
    public ReturnT<String> jobHandler(String param) {
        logger.info("定时任务执行时间：" + System.currentTimeMillis());
        return ReturnT.SUCCESS;
    }
}
