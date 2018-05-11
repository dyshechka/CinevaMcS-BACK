package cinema.cloud.service.order.client;

import cinema.cloud.service.order.api.domain.Film;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("film-service")
public interface FilmServiceClient {

    @GetMapping(value = "/films")
    Iterable<Film> getAllFilms();

    @GetMapping(value = "/film/{id}")
    Film getFilmById(@PathVariable("id") Integer id);

}
