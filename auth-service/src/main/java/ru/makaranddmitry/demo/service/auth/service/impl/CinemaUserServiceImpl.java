package ru.makaranddmitry.demo.service.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;
import ru.makaranddmitry.demo.service.auth.repository.CinemaUserRepository;
import ru.makaranddmitry.demo.service.auth.service.CinemaUserService;

@Service
public class CinemaUserServiceImpl implements CinemaUserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private CinemaUserRepository repository;

    @Override
    public void createUser(CinemaUser user) {
        CinemaUser existing = repository.getCinemaUserByUsername(user.getUsername());
        Assert.isNull(existing, "user already exists: " + user.getUsername());
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);
    }
}
