package com.fleet.exception.controller;

import com.fleet.exception.handler.BaseException;
import com.fleet.exception.json.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public R get() {
        return R.ok("hello！！！");
    }

    @RequestMapping("/ex")
    public R error() {
        throw new BaseException();
    }
}
