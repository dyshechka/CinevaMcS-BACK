package cinema.cloud.service.hall.repository;

import cinema.cloud.service.hall.domain.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {

    List<Seat> getSeatsByHallId(@Param("hallId") Integer hallId);

}
