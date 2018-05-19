package cinema.cloud.service.film.client;

import cinema.cloud.service.film.api.Seance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seance-service")
public interface SeanceClient {

    @GetMapping(value = "/film/{filmId}/seances")
    Iterable<Seance> getSeancesForFilms(@PathVariable("filmId") Integer filmId, @RequestParam("date") Long date);

}
