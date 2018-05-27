package cinema.cloud.course.project.service.film.repository;

import cinema.cloud.course.project.service.film.domain.RentalPeriod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalPeriodRepository extends CrudRepository<RentalPeriod, Integer> {
}
