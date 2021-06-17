package com.fleet.qywx;

import com.fleet.qywx.service.MsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgServiceTests {

    @Resource
    MsgService msgService;

    @Test
    public void sendTextMsg() {
        System.out.println(msgService.sendTextMsg("3175902", null, null, "消息提醒\n请登录<a href=\"http://work.weixin.qq.com\">企业微信</a>查看"));
    }
}
