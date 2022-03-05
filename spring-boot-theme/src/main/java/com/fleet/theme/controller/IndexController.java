package com.fleet.theme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author April Han
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

//    @Resource
//    private ThemeResolver themeResolver;
//
//    @RequestMapping("/index")
//    public String index(HttpServletRequest request, HttpServletResponse response, String theme) {
//        if (StringUtils.isNotEmpty(theme)) {
//            themeResolver.setThemeName(request, response, theme);
//        }
//        return "index";
//    }
}
