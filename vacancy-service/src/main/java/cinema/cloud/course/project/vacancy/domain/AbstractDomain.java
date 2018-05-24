package cinema.cloud.course.project.vacancy.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
abstract class AbstractDomain implements Serializable {
    private static final long serialVersionUID = -2061902443261750085L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
