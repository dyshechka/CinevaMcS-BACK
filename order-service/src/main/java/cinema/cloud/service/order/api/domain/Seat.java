package cinema.cloud.service.order.api.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Seat implements Serializable {
    private static final long serialVersionUID = 7054873782429028486L;

    private Boolean vip;

    private Integer number;
}