package cinema.cloud.course.project.service.auth.service.impl;

import cinema.cloud.course.project.service.auth.domain.CinemaUser;
import cinema.cloud.course.project.service.auth.repository.CinemaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CinemaUserDetailsService implements UserDetailsService {

    @Autowired
    private CinemaUserRepository cinemaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CinemaUser user = cinemaUserRepository.getCinemaUserByUsername(username);
        Assert.notNull(user, "User with such username wasn't found");
        return user;
    }
}
