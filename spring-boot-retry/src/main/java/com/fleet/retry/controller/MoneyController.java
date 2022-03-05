package com.fleet.retry.controller;

import com.fleet.retry.service.TransferService;
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

    @RequestMapping("/transfer")
    public void transfer() {
        transferService.transfer("张三", "李四", 3000);
    }
}
