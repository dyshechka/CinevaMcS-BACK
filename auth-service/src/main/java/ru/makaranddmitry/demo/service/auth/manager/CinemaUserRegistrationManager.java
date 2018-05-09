package ru.makaranddmitry.demo.service.auth.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;
import ru.makaranddmitry.demo.service.auth.request.CreateCinemaUserRequest;
import ru.makaranddmitry.demo.service.auth.service.CinemaUserService;

import java.util.List;

@Component
// TODO create interface
public class CinemaUserRegistrationManager {

    @Autowired
    private CinemaUserService cinemaUserService;

    public void createCinemaUser(CreateCinemaUserRequest request) {
        CinemaUser cinemaUser = new CinemaUser();
        cinemaUser.setUsername(request.getUsername());
        cinemaUser.setPassword(request.getPassword());
        cinemaUser.setEmail(request.getEmail());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        cinemaUser.setAuthorities(authorities);
        cinemaUserService.createUser(cinemaUser);
    }

}
