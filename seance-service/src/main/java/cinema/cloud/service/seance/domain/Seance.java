package cinema.cloud.service.seance.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seance")
@Data
@EqualsAndHashCode(callSuper = true)
public class Seance extends AbstractDomain {
    private static final long serialVersionUID = -8895361253158910155L;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime time;

    @Column(columnDefinition = "TINYINT")
    private Boolean availability;

    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "hall_id")
    private Integer hallId;
}
