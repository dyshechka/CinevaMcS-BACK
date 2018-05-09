package cinema.cloud.service.order.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
public class Order implements Serializable {
    private static final long serialVersionUID = 8463050142049856620L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
