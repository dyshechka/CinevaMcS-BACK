package cinema.cloud.course.project.service.order.api.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Seance implements Serializable {
    private static final long serialVersionUID = -8895361253158910155L;
    @NotNull
    private Integer id;
    @NotNull
    private Date time;
    @NotNull
    private Boolean availability;
    @NotNull
    private Integer filmId;
    @NotNull
    private Integer hallId;
}
