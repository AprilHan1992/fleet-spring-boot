package com.fleet.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CsvJobExecutionListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(CsvJobExecutionListener.class);

    private long startTime;

    private long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        logger.info("job process start...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        logger.info("job process end...");
        logger.info("execution time: " + (endTime - startTime) + "ms");
    }
}
