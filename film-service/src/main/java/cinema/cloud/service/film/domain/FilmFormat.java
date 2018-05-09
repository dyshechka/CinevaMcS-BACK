package cinema.cloud.service.film.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film_format")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "rentalPeriods")
public class FilmFormat extends AbstractDomain {
    private static final long serialVersionUID = -8855042288545766005L;
    @OneToMany(mappedBy = "format")
    @JsonIgnore
    private List<RentalPeriod> rentalPeriods = new ArrayList<>();
}
