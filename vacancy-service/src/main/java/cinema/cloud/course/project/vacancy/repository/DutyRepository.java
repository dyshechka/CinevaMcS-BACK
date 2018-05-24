package cinema.cloud.course.project.vacancy.repository;

import cinema.cloud.course.project.vacancy.domain.Duty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "duty", path = "duty")
public interface DutyRepository extends CrudRepository<Duty, Integer> {
}