package com.fleet.jpush.service;

import com.fleet.jpush.entity.JPush;

/**
 * @author April Han
 */
public interface JPushService {

    Boolean push(JPush jPush);

    Boolean push(JPush jPush, String... registrationId);
}
