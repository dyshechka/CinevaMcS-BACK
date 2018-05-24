package cinema.cloud.course.project.vacancy.repository;

import cinema.cloud.course.project.vacancy.domain.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "requirement", path = "requirement")
public interface RequirementRepository extends CrudRepository<Requirement, Integer> {
}
