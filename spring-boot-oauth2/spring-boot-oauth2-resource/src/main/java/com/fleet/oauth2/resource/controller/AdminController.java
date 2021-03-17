package com.fleet.oauth2.resource.controller;

import com.fleet.oauth2.resource.json.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/hello")
    public R hello() {
        return R.ok("你好，ADMIN 角色用户！");
    }
}
