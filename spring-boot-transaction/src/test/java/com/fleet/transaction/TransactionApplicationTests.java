package com.fleet.transaction;

import com.fleet.transaction.service.Transfer1Service;
import com.fleet.transaction.service.Transfer2Service;
import com.fleet.transaction.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionApplicationTests {

    @Resource
    private TransferService transferService;

    @Resource
    private Transfer1Service transfer1Service;

    @Resource
    private Transfer2Service transfer2Service;

    @Test
    public void transfer() {
        transferService.transfer("张三", "李四", 3000);
    }

    @Test
    public void transfer1() {
        transfer1Service.transfer("张三", "李四", 3000);
    }

    @Test
    public void transfer2() {
        transfer2Service.transfer("张三", "李四", 3000);
    }
}
