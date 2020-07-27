package com.fleet.quartz.service;

import com.fleet.quartz.entity.QuartzJobLog;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface QuartzJobLogService {

    Boolean insert(QuartzJobLog quartzJobLog);

    Boolean delete(QuartzJobLog quartzJobLog);

    Boolean deletes(Integer[] ids);

    Boolean update(QuartzJobLog quartzJobLog);

    QuartzJobLog get(QuartzJobLog quartzJobLog);

    List<QuartzJobLog> list(Map<String, Object> map);
}
