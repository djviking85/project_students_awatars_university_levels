package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")

public class InfoController {
    private final Logger logger = LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private Integer port;
    @GetMapping("/port")
    public Integer getPort() {
        logger.info("we are started prtogramm on port : = {}", port);
        return port;
    }
}
