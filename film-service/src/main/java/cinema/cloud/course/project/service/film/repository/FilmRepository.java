package cinema.cloud.course.project.service.film.repository;

import cinema.cloud.course.project.service.film.domain.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    Film getFilmsById(Integer id);
}
