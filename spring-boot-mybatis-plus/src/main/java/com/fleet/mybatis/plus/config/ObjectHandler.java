package com.fleet.mybatis.plus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author April Han
 */
@Component
public class ObjectHandler implements MetaObjectHandler {

    private static String CREATOR_ID = "creatorId";
    private static String CREATOR_NAME = "creatorName";
    private static String CREATE_TIME = "createTime";
    private static String DELETED = "deleted";
    private static String UPDATER_ID = "updaterId";
    private static String UPDATER_NAME = "updaterName";
    private static String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(CREATOR_ID)) {
            this.setFieldValByName(CREATOR_ID, 1L, metaObject);
        }
        if (metaObject.hasSetter(CREATOR_NAME)) {
            this.setFieldValByName(CREATOR_NAME, "fleet", metaObject);
        }
        if (metaObject.hasSetter(CREATE_TIME)) {
            this.setFieldValByName(CREATE_TIME, new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATER_ID)) {
            this.setFieldValByName(UPDATER_ID, 1L, metaObject);
        }
        if (metaObject.hasSetter(UPDATER_NAME)) {
            this.setFieldValByName(UPDATER_NAME, "fleet", metaObject);
        }
        if (metaObject.hasSetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        }
    }
}
