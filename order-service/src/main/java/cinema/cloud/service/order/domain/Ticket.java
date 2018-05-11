package cinema.cloud.service.order.domain;

import cinema.cloud.service.order.api.domain.Seat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Ticket implements Serializable {
    private static final long serialVersionUID = -1642346798652917708L;
    private String film;
    private LocalDateTime date;
    private Seat seat;
    private BigDecimal price;
}
