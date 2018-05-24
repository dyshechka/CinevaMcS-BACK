package cinema.cloud.course.project.seance.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeTimeInterval implements Serializable {
    private static final long serialVersionUID = 4294243881564931107L;

    private Long start;
    private Long finish;

    @JsonIgnore
    public boolean isLooped() {
        return start.equals(finish);
    }

}
