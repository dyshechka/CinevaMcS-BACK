package cinema.cloud.service.order.store;

import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import cinema.cloud.service.order.api.response.OrderResponse;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;

@Component
@SessionScope
@Data
public class OrderSession implements Serializable {

    private static final long serialVersionUID = 3589405968265176529L;

    private Seance seance;

    private List<Seat> seats;

    private OrderResponse orderResponse;

}
