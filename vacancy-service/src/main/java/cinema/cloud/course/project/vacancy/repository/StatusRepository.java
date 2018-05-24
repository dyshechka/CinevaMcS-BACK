package cinema.cloud.course.project.vacancy.repository;

import cinema.cloud.course.project.vacancy.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "status", path = "status")
public interface StatusRepository extends CrudRepository<Status, Integer> {
}
