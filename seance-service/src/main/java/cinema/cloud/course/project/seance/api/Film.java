package cinema.cloud.course.project.seance.api;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Film extends AbstractDomain {

    private static final long serialVersionUID = 5712452106362492423L;

    private Integer duration;

    private BigDecimal imdb;

    private RentalPeriod rentalPeriod;

    private List<AgeRestriction> ageRestrictions;

}
