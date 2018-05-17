package cinema.cloud.service.hall.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallWithRows {

    private String name;

    private List<RowWithSeats> rows;

}
