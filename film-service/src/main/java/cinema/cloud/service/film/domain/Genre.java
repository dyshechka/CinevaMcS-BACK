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
@Table(name = "genre")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Genre extends AbstractDomain {
    private static final long serialVersionUID = 2885513627962453594L;
    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
}
