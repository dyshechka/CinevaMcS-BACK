package cinema.cloud.service.seance.client;

import cinema.cloud.service.seance.api.Film;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("film-service")
public interface FilmClient {

    @PostMapping("/filmByIds")
    List<Film> getFilmsByIds(@RequestBody List<Integer> ids);

    @GetMapping("/filmsByDate")
    List<Film> getFilmsByDate(@RequestParam("dateTime") Long dateTime);
}
