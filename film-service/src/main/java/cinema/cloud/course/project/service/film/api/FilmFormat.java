package cinema.cloud.course.project.service.film.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmFormat implements Serializable {
    private static final long serialVersionUID = -8855042288545766005L;
    private Integer id;
    private String name;
}
