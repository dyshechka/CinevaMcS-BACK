package cinema.cloud.course.project.service.auth.repository;

import cinema.cloud.course.project.service.auth.domain.CinemaUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaUserRepository extends CrudRepository<CinemaUser, String> {
    CinemaUser getCinemaUserByUsername(String username);
}
