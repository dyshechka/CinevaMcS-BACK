package cinema.cloud.course.project.service.film.repository;

import cinema.cloud.course.project.service.film.domain.AgeRestriction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRestrictionsRepository extends CrudRepository<AgeRestriction, Integer> {

}
