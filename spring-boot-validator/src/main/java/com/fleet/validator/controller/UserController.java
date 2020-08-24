package com.fleet.validator.controller;

import com.fleet.validator.entity.User;
import com.fleet.validator.json.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author April Han
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/insert")
    public R insert(@RequestBody @Valid User user) {
        return R.ok();
    }

    @RequestMapping("/insert1")
    public R insert1(@RequestBody User user) {
        return R.ok();
    }
}
