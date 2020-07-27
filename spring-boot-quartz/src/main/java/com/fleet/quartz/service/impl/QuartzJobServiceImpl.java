package com.fleet.quartz.service.impl;

import com.fleet.quartz.dao.QuartzJobDao;
import com.fleet.quartz.entity.QuartzJob;
import com.fleet.quartz.enums.Enabled;
import com.fleet.quartz.service.QuartzJobService;
import com.fleet.quartz.util.QuartzUtil;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author April Han
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Resource
    private QuartzJobDao quartzJobDao;

    @Resource
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        Map<String, Object> map = new HashMap<>();
        map.put("enabled", Enabled.YES);
        List<QuartzJob> list = quartzJobDao.list(map);
        if (list != null) {
            for (QuartzJob job : list) {
                CronTrigger trigger = QuartzUtil.getCronTrigger(scheduler, job.getId());
                // 如果不存在，则创建
                if (trigger == null) {
                    QuartzUtil.insert(scheduler, job);
                } else {
                    QuartzUtil.update(scheduler, job);
                }
            }
        }
    }

    @Override
    public Boolean insert(QuartzJob quartzJob) {
        if (quartzJobDao.insert(quartzJob) == 0) {
            return false;
        }
        QuartzUtil.insert(scheduler, quartzJob);
        return true;
    }

    @Override
    public Boolean delete(QuartzJob quartzJob) {
        List<Integer> idList = quartzJobDao.idList(quartzJob);
        if (idList != null && idList.size() != 0) {
            if (quartzJobDao.delete(quartzJob) == 0) {
                return false;
            }
            for (Integer id : idList) {
                QuartzUtil.delete(scheduler, id);
            }
        }
        return true;
    }

    @Override
    public Boolean deletes(Integer[] ids) {
        if (quartzJobDao.deletes(ids) == 0) {
            return false;
        }
        for (Integer id : ids) {
            QuartzUtil.delete(scheduler, id);
        }
        return true;
    }

    @Override
    public Boolean update(QuartzJob quartzJob) {
        if (quartzJobDao.update(quartzJob) == 0) {
            return false;
        }
        QuartzUtil.update(scheduler, quartzJob);
        return true;
    }

    @Override
    public QuartzJob get(Integer id) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setId(id);
        return get(quartzJob);
    }

    @Override
    public QuartzJob get(QuartzJob quartzJob) {
        return quartzJobDao.get(quartzJob);
    }

    @Override
    public List<QuartzJob> list(Map<String, Object> map) {
        return quartzJobDao.list(map);
    }

    @Override
    public void run(Integer id) {
        QuartzUtil.run(scheduler, id);
    }

    @Override
    public void pause(Integer id) {
        QuartzUtil.pause(scheduler, id);
    }

    @Override
    public void resume(Integer id) {
        QuartzUtil.resume(scheduler, id);
    }
}
