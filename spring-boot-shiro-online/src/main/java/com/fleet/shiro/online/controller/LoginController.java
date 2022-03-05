package com.fleet.shiro.online.controller;

import com.fleet.shiro.online.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /**
     * 登陆
     */
    @RequestMapping(value = "/login")
    public String login() {
        String name = "admin";
        String password = "admin";
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(name, password));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "此用户不存在";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "密码错误";
        } catch (LockedAccountException e) {
            e.printStackTrace();
            return "此账号已被锁定";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "用户或密码错误";
        }
        return "登陆成功";
    }
}
