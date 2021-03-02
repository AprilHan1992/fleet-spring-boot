package com.fleet.webssh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author April Han
 */
@Controller
public class TerminalController {

    @GetMapping("terminal")
    public String hello1() {
        return "terminal";
    }
}
