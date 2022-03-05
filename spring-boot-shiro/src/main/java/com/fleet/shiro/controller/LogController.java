package com.fleet.shiro.controller;

import com.fleet.shiro.json.R;
import com.fleet.shiro.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class LogController {

    /**
     * 登陆
     */
    @RequestMapping(value = "/login")
    public R login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (NullPointerException | AuthenticationException e) {
            return R.error(e.getMessage());
        }
        return R.ok("登陆成功");
    }

    @RequestMapping(value = "/logout")
    public R logout() {
        ShiroUtil.logout();
        return R.ok("登出成功");
    }
}
