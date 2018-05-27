package cinema.cloud.course.project.service.film.repository;

import cinema.cloud.course.project.service.film.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
