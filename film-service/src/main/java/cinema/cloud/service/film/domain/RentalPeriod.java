package cinema.cloud.service.film.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental_period")
@Data
public class RentalPeriod implements Serializable {
    private static final long serialVersionUID = -5068103335849818663L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateBegin;
    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateEnd;
    @OneToMany(mappedBy = "rentalPeriod")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_film_format")
    private FilmFormat format;
}
