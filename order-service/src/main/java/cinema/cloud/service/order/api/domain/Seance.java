package cinema.cloud.service.order.api.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Seance implements Serializable {
    private static final long serialVersionUID = -8895361253158910155L;
    private Integer id;
    private LocalDateTime time;
    private Boolean availability;
    private Integer filmId;
    private Integer hallId;
}
