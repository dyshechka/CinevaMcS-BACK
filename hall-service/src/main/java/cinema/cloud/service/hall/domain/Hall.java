package cinema.cloud.service.hall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hall")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Hall extends AbstractDomain {
    private static final long serialVersionUID = 3289430599897436479L;
    @OneToMany(mappedBy = "hall")
    @JsonIgnore
    List<Seat> seats = new ArrayList<>();
    @Column
    private String name;
}
