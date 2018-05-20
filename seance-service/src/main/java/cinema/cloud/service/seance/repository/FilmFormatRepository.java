package cinema.cloud.service.seance.repository;

import cinema.cloud.service.seance.domain.FilmFormat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmFormatRepository extends CrudRepository<FilmFormat, Integer> {
}
