package com.fleet.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author April Han
 */
@RestController
public class VersionController {

    @Value("${version}")
    private String version;

    @RequestMapping("/version")
    public String version() {
        return version;
    }
}
