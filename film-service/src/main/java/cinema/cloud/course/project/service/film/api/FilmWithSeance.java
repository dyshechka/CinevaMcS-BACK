package cinema.cloud.course.project.service.film.api;

import cinema.cloud.course.project.service.film.domain.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmWithSeance {
    private Film film;
    private List<Seance> seances;
}
