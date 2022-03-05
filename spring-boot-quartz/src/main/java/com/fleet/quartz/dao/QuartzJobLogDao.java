package com.fleet.quartz.dao;

import com.fleet.quartz.entity.QuartzJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface QuartzJobLogDao {

    Integer insert(QuartzJobLog quartzJobLog);

    Integer delete(QuartzJobLog quartzJobLog);

    Integer deletes(Integer[] ids);

    Integer update(QuartzJobLog quartzJobLog);

    QuartzJobLog get(QuartzJobLog quartzJobLog);

    List<QuartzJobLog> list(Map<String, Object> map);
}
