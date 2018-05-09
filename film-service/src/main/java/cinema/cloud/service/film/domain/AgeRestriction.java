package cinema.cloud.service.film.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "age_restriction")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "films")
public class AgeRestriction extends AbstractDomain {
    private static final long serialVersionUID = -8601769985921848204L;
    @ManyToMany(mappedBy = "ageRestrictions")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
}
