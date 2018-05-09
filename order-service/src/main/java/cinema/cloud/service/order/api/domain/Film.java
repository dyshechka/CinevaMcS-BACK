package cinema.cloud.service.order.api.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Film extends AbstractDomain {

    private static final long serialVersionUID = 5712452106362492423L;

    private Integer duration;

    private BigDecimal imdb;

}
