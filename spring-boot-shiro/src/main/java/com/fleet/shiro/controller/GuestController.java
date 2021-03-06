package com.fleet.shiro.controller;

import com.fleet.shiro.json.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @RequestMapping("/hello")
    public R hello() {
        return R.ok("你好，游客！");
    }
}
