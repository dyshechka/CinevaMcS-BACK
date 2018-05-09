package cinema.cloud.service.film.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
abstract class AbstractDomain implements Serializable {

    private static final long serialVersionUID = -6480983749128439560L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
