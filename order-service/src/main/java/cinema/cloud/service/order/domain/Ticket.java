package cinema.cloud.service.order.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = -1642346798652917708L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String film;
    @Column
    private Date date;
    @Column(name = "seance_time")
    private Date seanceTime;
    @Column(name = "seat_id")
    private Integer seatId;
    @Column(name = "hall_id")
    private Integer hallId;
    @Column
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private TicketOrder order;
}
