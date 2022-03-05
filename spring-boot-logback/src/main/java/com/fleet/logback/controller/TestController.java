package com.fleet.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/log")
    public void log() {
        logger.trace("******trace******");
        logger.debug("******debug******");
        logger.info("******info******");
        logger.warn("******warn******");
        logger.error("******error******");
    }
}
