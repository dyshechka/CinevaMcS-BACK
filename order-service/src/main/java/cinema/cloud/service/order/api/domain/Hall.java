package cinema.cloud.service.order.api.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Hall extends AbstractDomain {
    private static final long serialVersionUID = 3289430599897436479L;

    private String name;
}
