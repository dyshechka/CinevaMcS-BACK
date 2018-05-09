package cinema.cloud.service.domain;

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
@Table(name = "country")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Country extends AbstractDomain {
    private static final long serialVersionUID = -7600774462189074222L;
    @ManyToMany(mappedBy = "countries")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
}
