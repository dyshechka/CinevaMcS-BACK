package cinema.cloud.service.seance.repository;

import cinema.cloud.service.seance.domain.Seance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Integer> {
    @Query("SELECT s FROM Seance s WHERE s.filmId = :filmId")
    List<Seance> getSeancesByFilmId(@Param("filmId") Integer filmId);

    Iterable<Seance> getSeanceByTimeBetweenAndHallId(Date start, Date finish, Integer hallId);
}
