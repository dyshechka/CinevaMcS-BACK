package cinema.cloud.course.project.service.order.client;

import cinema.cloud.course.project.service.order.api.domain.Seance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("seance-service")
public interface SeanceClient {

    @GetMapping("/seance/{id}")
    Seance getSeanceById(@PathVariable("id") Integer id);

}
