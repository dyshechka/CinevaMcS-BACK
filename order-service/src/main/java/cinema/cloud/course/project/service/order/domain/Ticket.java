package cinema.cloud.course.project.service.order.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@ToString(exclude = "order")
public class Ticket implements Serializable {
    private static final long serialVersionUID = -1642346798652917708L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String film;
    @Column
    private Date date;
    @Column(name = "seance_id")
    private Integer seanceId;
    @Column(name = "seat_id")
    private Integer seatId;
    @Column
    private BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private TicketOrder order;
}
