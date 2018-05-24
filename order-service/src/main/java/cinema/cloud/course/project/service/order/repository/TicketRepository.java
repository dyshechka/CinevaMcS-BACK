package cinema.cloud.course.project.service.order.repository;

import cinema.cloud.course.project.service.order.domain.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("select t from Ticket t where t.seanceId = :seanceId")
    List<Ticket> getTicketsBySeanceId(@Param("seanceId") Integer seanceId);

}
