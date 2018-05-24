package cinema.cloud.course.project.vacancy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Requirement extends AbstractDomain {
    private static final long serialVersionUID = 915588093338394014L;

    @ManyToMany(mappedBy = "requirements")
    @JsonIgnore
    private List<Vacancy> vacancies = new ArrayList<>();
}
