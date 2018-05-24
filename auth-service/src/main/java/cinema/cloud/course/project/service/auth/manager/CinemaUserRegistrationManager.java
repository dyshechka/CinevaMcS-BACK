package cinema.cloud.course.project.service.auth.manager;

import cinema.cloud.course.project.service.auth.domain.CinemaUser;
import cinema.cloud.course.project.service.auth.request.CreateCinemaUserRequest;
import cinema.cloud.course.project.service.auth.service.CinemaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CinemaUserRegistrationManager {

    @Autowired
    private CinemaUserService cinemaUserService;

    public void createCinemaUser(CreateCinemaUserRequest request) {
        CinemaUser cinemaUser = new CinemaUser();
        cinemaUser.setUsername(request.getUsername());
        cinemaUser.setPassword(request.getPassword());
        cinemaUser.setEmail(request.getEmail());
        cinemaUser.setFirstName(request.getFirstName());
        cinemaUser.setLastName(request.getLastName());
        cinemaUser.setSex(request.getSex());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        cinemaUser.setAuthorities(authorities);
        cinemaUserService.createUser(cinemaUser);
    }

}
