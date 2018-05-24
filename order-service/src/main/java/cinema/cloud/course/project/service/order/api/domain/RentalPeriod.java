package cinema.cloud.course.project.service.order.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class RentalPeriod implements Serializable {
    private static final long serialVersionUID = -5068103335849818663L;
    private Integer id;
    private DateTime dateBegin;
    private DateTime dateEnd;
}
