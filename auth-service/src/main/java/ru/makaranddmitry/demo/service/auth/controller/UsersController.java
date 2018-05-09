package ru.makaranddmitry.demo.service.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.makaranddmitry.demo.service.auth.manager.CinemaUserRegistrationManager;
import ru.makaranddmitry.demo.service.auth.request.CreateCinemaUserRequest;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private CinemaUserRegistrationManager cinemaUserRegistrationManager;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createUser(@RequestBody CreateCinemaUserRequest request) {
        cinemaUserRegistrationManager.createCinemaUser(request);
    }
}
