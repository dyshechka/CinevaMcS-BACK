package ru.makaranddmitry.demo.service.test2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final String RESULT_STRING = "TEST";

    @RequestMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return RESULT_STRING;
    }
}
