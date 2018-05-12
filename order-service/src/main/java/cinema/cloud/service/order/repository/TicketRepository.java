package cinema.cloud.service.order.repository;

import cinema.cloud.service.order.domain.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("select t from Ticket t where seance_time = :seanceTime and hall_id = :hall_id and seat_id in :ids")
    List<Ticket> getTicketsByIdsAndAndSeanceTime(
            @Param("ids") List<Integer> ids,
            @Param("seanceTime") Date seanceTime,
            @Param("hall_id") Integer hallId);

}
