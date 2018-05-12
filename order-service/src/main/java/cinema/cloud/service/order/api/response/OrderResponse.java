package cinema.cloud.service.order.api.response;

import cinema.cloud.service.order.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse implements Serializable {
    private static final long serialVersionUID = -1626626867489371973L;
    private List<Ticket> tickets;
    private BigDecimal commonCost;
}
