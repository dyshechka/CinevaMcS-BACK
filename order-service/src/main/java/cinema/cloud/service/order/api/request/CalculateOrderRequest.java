package cinema.cloud.service.order.api.request;

import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CalculateOrderRequest {

    private static final long serialVersionUID = -4626532520594171108L;

    private Seance seance;

    private List<Seat> seats;

}
