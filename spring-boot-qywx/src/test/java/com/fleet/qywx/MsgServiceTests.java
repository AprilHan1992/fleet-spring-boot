package com.fleet.qywx;

import com.fleet.qywx.service.MsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgServiceTests {

    @Resource
    MsgService msgService;

    @Test
    public void sendTextMsg() {
        System.out.println(msgService.sendTextMsg("3175902", null, null, "活动报名提醒\n开始日期：2021年5月20日\n截止日期：2021年6月20日\n详情请登录<a href=\"http://www.fleetsoft.com.cn\">fleet网站</a>查看"));
    }

    @Test
    public void sendImageMsg() {
        System.out.println(msgService.sendImageMsg("3175902", null, null, "3O_60-6Jhs1g8D9AWSf1eP1cjcbdJa9ThDIG9G6c_nLMO7SWvyJ7ANigmhMws56VC"));
    }

    @Test
    public void sendVoiceMsg() {
        System.out.println(msgService.sendVoiceMsg("3175902", null, null, "3MfIHDmEgZfLIZPn_JfWPzU2gOV5eukNfUGb0wUQ6_68"));
    }

    @Test
    public void sendVideoMsg() {
        System.out.println(msgService.sendVideoMsg("3175902", null, null, "3V5qqrQxqvpklRfiPN68074bQkl0pjoRwfYvuW_5jflsdI1KcM30ivfxI64jTTnqh", "测试视频", "这是测试MP4视频"));
    }

    @Test
    public void sendFileMsg() {
        System.out.println(msgService.sendFileMsg("3175902", null, null, "3tz3RhCfnPYDxal5GTtAE7lDz7S_cA_Jc-Zoqe2bpbzQ"));
    }

    @Test
    public void sendTextCardMsg() {
        System.out.println(msgService.sendTextCardMsg("3175902", null, null, "活动报名提醒", "<div class=\"gray\">开始日期：2021年5月20日</div><div class=\"highlight\">截止日期：2021年6月20日</div><div class=\"normal\">详情请登录fleet网站查看</div>", "http://www.fleetsoft.com.cn"));
    }

    @Test
    public void sendNewsMsg() {
        List<Map<String, Object>> articles = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("title", "活动报名提醒");
        map.put("description", "详情请登录fleet网站查看");
        map.put("url", "http://www.fleetsoft.com.cn");
        map.put("picurl", "https://dummyimage.com/1068x455/000/fff");
        articles.add(map);
        System.out.println(msgService.sendNewsMsg("3175902", null, null, articles));
    }

    @Test
    public void sendMarkdownMsg() {
        String content = "活动报名提醒，稍后会同步发送到`邮箱` " +
                "\n>**活动详情** " +
                "\n>地　点：<font color=\"info\">深圳</font>  " +
                "\n>举办方：<font color=\"comment\">fleet</font>   " +
                "\n>开　始：<font color=\"info\">2021年5月20日</font> " +
                "\n>截　止：<font color=\"warning\">2021年6月20日</font> " +
                "\n> " +
                "\n>如需查看详情，请点击：[详情](http://www.fleetsoft.com.cn)";
        System.out.println(msgService.sendMarkdownMsg("3175902", null, null, content));
    }
}
