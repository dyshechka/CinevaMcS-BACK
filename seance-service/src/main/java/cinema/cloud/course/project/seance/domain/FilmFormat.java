package cinema.cloud.course.project.seance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film_format")
@Data
public class FilmFormat implements Serializable {
    private static final long serialVersionUID = -8855042288545766005L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "format")
    @JsonIgnore
    private List<Seance> seances = new ArrayList<>();
}
