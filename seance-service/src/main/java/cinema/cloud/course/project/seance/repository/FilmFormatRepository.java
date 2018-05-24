package cinema.cloud.course.project.seance.repository;

import cinema.cloud.course.project.seance.domain.FilmFormat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmFormatRepository extends CrudRepository<FilmFormat, Integer> {
}
