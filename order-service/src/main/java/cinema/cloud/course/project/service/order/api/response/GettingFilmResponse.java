package cinema.cloud.course.project.service.order.api.response;

import cinema.cloud.course.project.service.order.api.domain.AgeRestriction;
import cinema.cloud.course.project.service.order.api.domain.Country;
import cinema.cloud.course.project.service.order.api.domain.Genre;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GettingFilmResponse {
    private Integer id;
    private String name;
    private Integer duration;
    private BigDecimal imdb;
    private List<Country> countries;
    private List<AgeRestriction> ageRestrictions;
    private List<Genre> genres;
}
