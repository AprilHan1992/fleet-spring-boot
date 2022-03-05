package com.fleet.quartz.service.impl;

import com.fleet.quartz.dao.QuartzJobLogDao;
import com.fleet.quartz.entity.QuartzJobLog;
import com.fleet.quartz.service.QuartzJobLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Component
@Service
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

    @Resource
    private QuartzJobLogDao quartzJobLogDao;

    @Async
    @Override
    public Boolean insert(QuartzJobLog quartzJobLog) {
        return quartzJobLogDao.insert(quartzJobLog) != 0;
    }

    @Override
    public Boolean delete(QuartzJobLog quartzJobLog) {
        return quartzJobLogDao.delete(quartzJobLog) != 0;
    }

    @Override
    public Boolean deletes(Integer[] ids) {
        return quartzJobLogDao.deletes(ids) != 0;
    }

    @Override
    public Boolean update(QuartzJobLog quartzJobLog) {
        return quartzJobLogDao.update(quartzJobLog) != 0;
    }

    @Override
    public QuartzJobLog get(QuartzJobLog quartzJobLog) {
        return quartzJobLogDao.get(quartzJobLog);
    }

    @Override
    public List<QuartzJobLog> list(Map<String, Object> map) {
        return quartzJobLogDao.list(map);
    }
}
