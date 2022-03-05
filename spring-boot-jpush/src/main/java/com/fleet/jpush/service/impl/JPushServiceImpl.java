package com.fleet.jpush.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.fleet.jpush.config.JPushConfig;
import com.fleet.jpush.entity.JPush;
import com.fleet.jpush.service.JPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Service
public class JPushServiceImpl implements JPushService {

    private static final Logger logger = LoggerFactory.getLogger(JPushServiceImpl.class);

    @Resource
    JPushConfig jPushConfig;

    @Override
    public Boolean push(JPush jPush) {
        PushPayload.Builder pb = PushPayload.newBuilder();
        if (jPush.getPlatform().equalsIgnoreCase("all")) {
            pb.setPlatform(Platform.all());
            pb.setAudience(Audience.all());
            pb.setNotification(Notification.alert(jPush.getAlert()));
        } else if (jPush.getPlatform().equalsIgnoreCase("ios")) {
            pb.setPlatform(Platform.ios());
            pb.setAudience(Audience.all());
            pb.setNotification(Notification.ios(jPush.getAlert(), jPush.getExtras()));
        } else if (jPush.getPlatform().equalsIgnoreCase("android")) {
            pb.setPlatform(Platform.android());
            pb.setAudience(Audience.all());
            pb.setNotification(Notification.android(jPush.getAlert(), jPush.getTitle(), jPush.getExtras()));
        } else if (jPush.getPlatform().equalsIgnoreCase("winphone")) {
            pb.setPlatform(Platform.winphone());
            pb.setAudience(Audience.all());
            pb.setNotification(Notification.winphone(jPush.getAlert(), jPush.getExtras()));
        }
        return push(pb.build());
    }

    @Override
    public Boolean push(JPush jPush, String... registrationId) {
        PushPayload.Builder pb = PushPayload.newBuilder();
        if (jPush.getPlatform().equalsIgnoreCase("all")) {
            pb.setPlatform(Platform.all());
            pb.setAudience(Audience.registrationId(registrationId));
            pb.setNotification(Notification.alert(jPush.getAlert()));
        } else if (jPush.getPlatform().equalsIgnoreCase("ios")) {
            pb.setPlatform(Platform.ios());
            pb.setAudience(Audience.registrationId(registrationId));
            pb.setNotification(Notification.ios(jPush.getAlert(), jPush.getExtras()));
        } else if (jPush.getPlatform().equalsIgnoreCase("android")) {
            pb.setPlatform(Platform.android());
            pb.setAudience(Audience.registrationId(registrationId));
            pb.setNotification(Notification.android(jPush.getAlert(), jPush.getTitle(), jPush.getExtras()));
        } else if (jPush.getPlatform().equalsIgnoreCase("winphone")) {
            pb.setPlatform(Platform.winphone());
            pb.setAudience(Audience.registrationId(registrationId));
            pb.setNotification(Notification.winphone(jPush.getAlert(), jPush.getExtras()));
        }
        return push(pb.build());
    }

    private Boolean push(PushPayload pushPayload) {
        logger.info("发送极光推送请求: {}", pushPayload);
        PushResult pushResult = null;
        try {
            pushResult = jPushConfig.getJPushClient().sendPush(pushPayload);
        } catch (APIConnectionException e) {
            logger.error("极光推送连接异常: ", e);
        } catch (APIRequestException e) {
            logger.error("极光推送请求异常: ", e);
        }
        if (pushResult != null && pushResult.isResultOK()) {
            logger.info("极光推送请求成功: {}", pushResult);
            return true;
        } else {
            logger.info("极光推送请求失败: {}", pushResult);
            return false;
        }
    }
}
