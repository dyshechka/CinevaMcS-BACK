package cinema.cloud.service.hall.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Seat extends AbstractDomain {

    private static final long serialVersionUID = 7054873782429028486L;

    @Column(columnDefinition = "TINYINT")
    private Boolean vip;

    @Column
    private Integer number;

    @Column
    private Integer row;

    @ManyToOne
    @JoinColumn(name = "id_hall")
    private Hall hall;

    @Transient
    private Boolean free;
}
