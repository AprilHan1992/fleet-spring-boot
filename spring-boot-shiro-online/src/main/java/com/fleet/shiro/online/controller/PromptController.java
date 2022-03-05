package com.fleet.shiro.online.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptController {

    /**
     * 未登录提示
     */
    @RequestMapping(value = "/notIn")
    public String notIn() {
        return "未登录";
    }

    /**
     * 已登录提示
     */
    @RequestMapping(value = "/in")
    public String in() {
        return "已登录";
    }

    /**
     * 已登出提示
     *
     * @return
     */
    @RequestMapping(value = "/out")
    public String out() {
        return "已登出";
    }

    /**
     * 已登出提示
     *
     * @return
     */
    @RequestMapping(value = "/unauth")
    public String unauth() {
        return "无权限";
    }
}
