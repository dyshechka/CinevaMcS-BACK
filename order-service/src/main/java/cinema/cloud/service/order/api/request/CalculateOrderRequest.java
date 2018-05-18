package cinema.cloud.service.order.api.request;

import lombok.Data;

import java.util.List;

@Data
public class CalculateOrderRequest {

    private static final long serialVersionUID = -4626532520594171108L;

    private Integer seanceId;

    private List<Integer> seatIds;

}
