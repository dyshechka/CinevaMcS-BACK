package cinema.cloud.service.order.repository;

import cinema.cloud.service.order.domain.TicketOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TicketOrder, Integer> {

    List<TicketOrder> getTicketOrderByUsername(String username);

}
