package cinema.cloud.course.project.service.film.repository;

import cinema.cloud.course.project.service.film.domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
}
