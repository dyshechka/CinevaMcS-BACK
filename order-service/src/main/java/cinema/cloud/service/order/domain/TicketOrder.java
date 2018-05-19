package cinema.cloud.service.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ticket_order")
public class TicketOrder implements Serializable {
    private static final long serialVersionUID = 8463050142049856620L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    @JsonIgnore
    private List<Ticket> tickets;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "username")
    private String username;

}
