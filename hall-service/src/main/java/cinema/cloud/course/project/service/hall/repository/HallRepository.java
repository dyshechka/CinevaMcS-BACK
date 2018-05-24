package cinema.cloud.course.project.service.hall.repository;

import cinema.cloud.course.project.service.hall.domain.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends CrudRepository<Hall, Integer> {

}
