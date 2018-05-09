package cinema.cloud.service.order.api.domain;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
abstract class AbstractDomain implements Serializable {

    private static final long serialVersionUID = -6480983749128439560L;

    private Integer id;

    private String name;
}
