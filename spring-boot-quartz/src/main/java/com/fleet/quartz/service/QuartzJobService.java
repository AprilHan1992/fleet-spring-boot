package com.fleet.quartz.service;

import com.fleet.quartz.entity.QuartzJob;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
public interface QuartzJobService {

    Boolean insert(QuartzJob quartzJob);

    Boolean delete(QuartzJob quartzJob);

    Boolean deletes(Integer[] ids);

    Boolean update(QuartzJob quartzJob);

    QuartzJob get(Integer id);

    QuartzJob get(QuartzJob quartzJob);

    List<QuartzJob> list(Map<String, Object> map);

    void run(Integer id);

    void pause(Integer id);

    void resume(Integer id);
}
