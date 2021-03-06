package cinema.cloud.course.project.service.film.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Seance implements Serializable {
    private static final long serialVersionUID = -8895361253158910155L;
    private Integer id;
    private Date time;
    private Boolean availability;
    private Integer filmId;
    private Integer hallId;
    private FilmFormat format;
}
