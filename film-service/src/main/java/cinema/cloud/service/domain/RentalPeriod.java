package cinema.cloud.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental_period")
@Data
@ToString(callSuper = true)
public class RentalPeriod implements Serializable {
    private static final long serialVersionUID = -5068103335849818663L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private LocalDateTime dateBegin;
    @Column
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private LocalDateTime dateEnd;
    @ManyToMany(mappedBy = "rentalPeriods")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_film_format")
    private FilmFormat format;
}
