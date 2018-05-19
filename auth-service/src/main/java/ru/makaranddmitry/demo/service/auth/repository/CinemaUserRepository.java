package ru.makaranddmitry.demo.service.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.makaranddmitry.demo.service.auth.domain.CinemaUser;

@Repository
public interface CinemaUserRepository extends CrudRepository<CinemaUser, String> {
    CinemaUser getCinemaUserByUsername(String username);
}
