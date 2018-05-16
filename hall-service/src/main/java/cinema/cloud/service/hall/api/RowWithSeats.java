package cinema.cloud.service.hall.api;

import cinema.cloud.service.hall.domain.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RowWithSeats implements Serializable {

    private Integer rowNumber;

    private List<Seat> seats;

}
