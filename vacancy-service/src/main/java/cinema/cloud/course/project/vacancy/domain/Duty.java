package cinema.cloud.course.project.vacancy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "duty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Duty extends AbstractDomain {
    private static final long serialVersionUID = 8814764443554408187L;

    @ManyToMany(mappedBy = "duties")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
