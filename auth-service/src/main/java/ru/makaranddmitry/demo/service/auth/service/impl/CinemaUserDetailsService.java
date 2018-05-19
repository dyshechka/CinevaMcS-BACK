package ru.makaranddmitry.demo.service.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;
import ru.makaranddmitry.demo.service.auth.repository.CinemaUserRepository;

@Service
public class CinemaUserDetailsService implements UserDetailsService {

    @Autowired
    private CinemaUserRepository cinemaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CinemaUser user = cinemaUserRepository.findOne(username);
        Assert.notNull(user, "User with such username wasn't found");
        return user;
    }
}
