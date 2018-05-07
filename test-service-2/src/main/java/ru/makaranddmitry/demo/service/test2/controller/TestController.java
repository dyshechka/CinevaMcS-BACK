package ru.makaranddmitry.demo.service.test2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.makaranddmitry.demo.service.test2.client.TestClient;

@RestController
public class TestController {

    private final TestClient client;

    @Autowired
    public TestController(TestClient client) {
        this.client = client;
    }

    @RequestMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        return String.format("'%s' via Feign client from test-service-1", client.getStringFromTest());
    }

    @RequestMapping("/test2")
    @PreAuthorize("hasRole('USER')")
    public String tes2t() {
        return "TEST_2";
    }
}
