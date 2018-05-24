package cinema.cloud.course.project.vacancy.repository;

import cinema.cloud.course.project.vacancy.domain.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vacancy", path = "vacancy")
public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
}
