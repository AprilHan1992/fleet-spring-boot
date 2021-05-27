package com.fleet.exception.controller;

import com.fleet.exception.config.exception.BaseException;
import com.fleet.exception.json.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public R get() {
        return R.ok("hello！！！");
    }

    @RequestMapping("/bex")
    public R bex() {
        throw new BaseException("自定义错误");
    }

    @RequestMapping("/ex")
    public R ex() throws Exception {
        throw new Exception();
    }
}
