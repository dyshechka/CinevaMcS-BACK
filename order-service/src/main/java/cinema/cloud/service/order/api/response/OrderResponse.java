package cinema.cloud.service.order.api.response;

import cinema.cloud.service.order.domain.Ticket;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse implements Serializable {
    private static final long serialVersionUID = -1626626867489371973L;
    private Integer orderId;
    private List<Ticket> tickets;
    private BigDecimal commonCost;

    public OrderResponse() {
    }

    public OrderResponse(List<Ticket> tickets, BigDecimal commonCost) {
        this.tickets = tickets;
        this.commonCost = commonCost;
    }
}
