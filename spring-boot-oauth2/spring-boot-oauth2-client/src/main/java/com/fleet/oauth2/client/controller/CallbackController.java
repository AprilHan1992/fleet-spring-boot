package com.fleet.oauth2.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class CallbackController {

    /**
     * 回调
     */
    @GetMapping("/callback")
    public String callback() {
        return "回调成功";
    }
}
