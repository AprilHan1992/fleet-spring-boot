package com.fleet.online.controller;

import com.fleet.online.json.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class LoginController {

    /**
     * 登陆
     *
     * @param name 账户
     * @param pwd  密码
     */
    @GetMapping("/login")
    public R login(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        session.setAttribute("name", name);
        return R.ok();
    }

    @RequestMapping("/session")
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println(session.getMaxInactiveInterval());
            return (String) session.getAttribute("name");
        }
        return "";
    }
}
