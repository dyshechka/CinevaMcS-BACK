package cinema.cloud.service.seance.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seance")
@Data
public class Seance {
    private static final long serialVersionUID = -8895361253158910155L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date time;

    @Column(columnDefinition = "TINYINT")
    private Boolean availability;

    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "hall_id")
    private Integer hallId;
}
