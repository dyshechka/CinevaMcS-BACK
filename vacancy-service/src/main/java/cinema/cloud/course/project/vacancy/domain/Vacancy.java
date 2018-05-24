package cinema.cloud.course.project.vacancy.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vacancy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vacancy extends AbstractDomain {
    private static final long serialVersionUID = 5494311274605312659L;

    @Column
    private String schedule;

    @Column(name = "work_experience")
    private String workExperience;

    @Column
    private BigDecimal salary;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "duty_vacancy",
            joinColumns = {@JoinColumn(name = "id_duty")},
            inverseJoinColumns = {@JoinColumn(name = "id_vacancy")}
    )
    private List<Duty> duties = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "requirement_vacancy",
            joinColumns = {@JoinColumn(name = "id_requirement")},
            inverseJoinColumns = {@JoinColumn(name = "id_vacancy")}
    )
    private List<Requirement> requirements = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "status_vacancy",
            joinColumns = {@JoinColumn(name = "id_vacancy")},
            inverseJoinColumns = {@JoinColumn(name = "id_status")}
    )
    private List<Status> statuses = new ArrayList<>();
}
