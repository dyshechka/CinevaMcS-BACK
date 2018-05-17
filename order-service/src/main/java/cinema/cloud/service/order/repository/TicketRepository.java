package cinema.cloud.service.order.repository;

import cinema.cloud.service.order.domain.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("select t from Ticket t where t.seanceId = :seanceId and t.seatId in :ids")
    List<Ticket> getTicketsByIdsAndAndSeanceTime(
            @Param("ids") List<Integer> ids,
            @Param("seanceId") Integer hallId);

}
