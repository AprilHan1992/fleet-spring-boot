package com.fleet.quartz.dao;

import com.fleet.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Mapper
@Repository
public interface QuartzJobDao {

    Integer insert(QuartzJob quartzJob);

    Integer delete(QuartzJob quartzJob);

    Integer deletes(Integer[] ids);

    Integer update(QuartzJob quartzJob);

    QuartzJob get(QuartzJob quartzJob);

    List<QuartzJob> list(Map<String, Object> map);

    List<Integer> idList(QuartzJob quartzJob);
}
