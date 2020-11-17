package com.fleet.transaction.controller;

import com.fleet.transaction.service.Transfer1Service;
import com.fleet.transaction.service.Transfer2Service;
import com.fleet.transaction.service.TransferService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/money")
public class MoneyController {

    @Resource
    private TransferService transferService;

    @Resource
    private Transfer1Service transfer1Service;

    @Resource
    private Transfer2Service transfer2Service;

    @RequestMapping("/transfer")
    public void transfer() {
        transferService.transfer("张三", "李四", 3000);
    }

    @RequestMapping("/transfer1")
    public void transfer1() {
        transfer1Service.transfer("张三", "李四", 3000);
    }

    @RequestMapping("/transfer2")
    public void transfer2() {
        transfer2Service.transfer("张三", "李四", 3000);
    }
}
