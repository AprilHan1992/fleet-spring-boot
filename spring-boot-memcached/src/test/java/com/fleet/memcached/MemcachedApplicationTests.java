package com.fleet.memcached;

import com.fleet.memcached.config.MemcachedRunner;
import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedApplicationTests {

    @Resource
    private MemcachedRunner memcachedRunner;

    @Test
    public void test() {
        MemcachedClient client = memcachedRunner.getClient();
        client.set("key", 1000, "fleet");
        System.out.println(client.get("key").toString());
    }
}
