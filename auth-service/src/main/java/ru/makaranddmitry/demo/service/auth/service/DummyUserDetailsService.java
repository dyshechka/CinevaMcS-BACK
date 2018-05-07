package ru.makaranddmitry.demo.service.auth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DummyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(
                username,
                "$2a$10$WKM0V1kIkNdrGsRasRvYHOLKVdrgoMmY7EGL4VRvimQMgaGsEe7zy",
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );
    }
}
