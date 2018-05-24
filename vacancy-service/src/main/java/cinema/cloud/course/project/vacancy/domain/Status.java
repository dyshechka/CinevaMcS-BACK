package cinema.cloud.course.project.vacancy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Status extends AbstractDomain {
    private static final long serialVersionUID = -4192261727636021691L;

    @ManyToMany(mappedBy = "statuses")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
