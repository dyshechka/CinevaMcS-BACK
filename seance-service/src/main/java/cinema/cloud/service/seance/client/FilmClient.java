package cinema.cloud.service.seance.client;

import cinema.cloud.service.seance.api.Film;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("film-service")
public interface FilmClient {

    @PostMapping("/filmByIds")
    List<Film> getFilmsByIds(@RequestBody List<Integer> ids);

}
