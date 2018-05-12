package cinema.cloud.service.order.repository;

import cinema.cloud.service.order.domain.TicketOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TicketOrder, Integer> {

}
