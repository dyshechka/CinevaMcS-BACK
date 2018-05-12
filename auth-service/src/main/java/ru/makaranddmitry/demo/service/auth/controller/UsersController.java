package ru.makaranddmitry.demo.service.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;
import ru.makaranddmitry.demo.service.auth.domain.UserInfo;
import ru.makaranddmitry.demo.service.auth.manager.CinemaUserRegistrationManager;
import ru.makaranddmitry.demo.service.auth.request.CreateCinemaUserRequest;

import java.security.Principal;
import java.util.ArrayList;

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

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public UserInfo getUserInfo(Principal principal) {
        UserInfo userInfo = new UserInfo();
        if (!(principal instanceof OAuth2Authentication)) {
            return null;
        }
        if (!(((OAuth2Authentication) principal).getUserAuthentication().getPrincipal() instanceof CinemaUser)) {
            return null;
        }
        CinemaUser cinemaUser = ((CinemaUser) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal());
        userInfo.setUsername(cinemaUser.getUsername());
        userInfo.setEmail(cinemaUser.getEmail());
        ArrayList<? extends GrantedAuthority> grantedAuthorities = new ArrayList<>(cinemaUser.getAuthorities());
        SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) grantedAuthorities.get(0);
        userInfo.setRole(grantedAuthority.getAuthority());
        return userInfo;
    }
}
