package cinema.cloud.service.repository;

import cinema.cloud.service.domain.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film, Integer> {

}
