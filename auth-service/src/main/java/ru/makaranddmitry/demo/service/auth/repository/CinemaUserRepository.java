package ru.makaranddmitry.demo.service.auth.repository;

import org.springframework.data.repository.CrudRepository;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;

public interface CinemaUserRepository extends CrudRepository<CinemaUser, String> {
    CinemaUser findByUsername(String username);
}
