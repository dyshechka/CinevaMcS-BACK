package cinema.cloud.service.order.api.response;

import cinema.cloud.service.order.api.domain.Seance;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GettingSeanceResponse implements Serializable {
    private static final long serialVersionUID = 5250411245756088551L;

    List<Seance> seances;
}
