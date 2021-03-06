package cinema.cloud.course.project.seance.repository;

import cinema.cloud.course.project.seance.domain.Seance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Integer> {
    @Query("SELECT s FROM Seance s WHERE s.filmId = :filmId and s.time between :currentDate and :targetDate")
    List<Seance> getSeancesByFilmId(
            @Param("filmId") Integer filmId,
            @Param("currentDate") Date currentDate,
            @Param("targetDate") Date targetDate);

    Iterable<Seance> getSeanceByTimeBetweenAndHallId(Date start, Date finish, Integer hallId);
}
