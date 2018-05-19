package cinema.cloud.service.order.api.response;

import cinema.cloud.service.order.domain.Ticket;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CinemaOrder implements Serializable {
    private static final long serialVersionUID = -1626626867489371973L;
    private Integer orderId;
    private List<Ticket> tickets;
    private BigDecimal commonCost;
    private Date orderDate;

    public CinemaOrder() {
    }

    public CinemaOrder(List<Ticket> tickets, BigDecimal commonCost) {
        this.tickets = tickets;
        this.commonCost = commonCost;
    }
}
