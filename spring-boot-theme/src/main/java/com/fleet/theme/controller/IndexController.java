package com.fleet.theme.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ThemeResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author April Han
 */
@Controller
public class IndexController {

    @Resource
    private ThemeResolver themeResolver;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, String theme) {
        if (StringUtils.isNotEmpty(theme)) {
            themeResolver.setThemeName(request, response, theme);
        }
        return "index";
    }
}
