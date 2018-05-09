package cinema.cloud.service.order.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ApproveResponse {

    private Map<String, String> filmData;

    private Map<String, String> seanceData;

    private Map<String, String> seatDate;

    private BigDecimal price;

}
