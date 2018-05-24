package cinema.cloud.course.project.service.order.repository;

import cinema.cloud.course.project.service.order.domain.TicketOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TicketOrder, Integer> {

    List<TicketOrder> getTicketOrderByUsername(String username);

}
