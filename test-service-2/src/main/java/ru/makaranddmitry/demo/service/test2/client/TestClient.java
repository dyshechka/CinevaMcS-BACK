package ru.makaranddmitry.demo.service.test2.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("test-service-1")
public interface TestClient {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String getStringFromTest();
}
