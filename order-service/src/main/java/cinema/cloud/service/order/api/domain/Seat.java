package cinema.cloud.service.order.api.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Seat implements Serializable {
    private static final long serialVersionUID = 7054873782429028486L;
    @NotNull
    private Integer id;
    @NotNull
    private Boolean vip;
    @NotNull
    private Integer number;
    @NotNull
    private Integer row;
    @NotNull
    private Hall hall;
}