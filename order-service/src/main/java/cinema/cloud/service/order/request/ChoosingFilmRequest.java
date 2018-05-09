package cinema.cloud.service.order.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ChoosingFilmRequest extends AbstractRequest {

    private static final long serialVersionUID = -4626532520594171108L;

    @NotNull
    private Integer filmId;

}
