package cinema.cloud.service.seance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "seance")
@Data
@EqualsAndHashCode(callSuper = true)
public class Seance extends AbstractDomain {
    private static final long serialVersionUID = -8895361253158910155L;

    @Column
    private Date time;

    @Column(columnDefinition = "TINYINT")
    private Boolean availability;

    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "hall_id")
    private Integer hallId;
}
