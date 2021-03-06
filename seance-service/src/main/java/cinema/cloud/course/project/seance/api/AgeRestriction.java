package cinema.cloud.course.project.seance.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = "films")
public class AgeRestriction extends AbstractDomain {
    private static final long serialVersionUID = -8601769985921848204L;
}
