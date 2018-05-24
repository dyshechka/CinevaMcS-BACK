package cinema.cloud.course.project.seance.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetSeancesByDateAndHallRequest implements Serializable {
    private static final long serialVersionUID = 5564605419993535191L;

    private Date time;

    private Integer hallId;

}
