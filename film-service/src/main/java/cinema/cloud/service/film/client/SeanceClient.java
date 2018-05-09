package cinema.cloud.service.film.client;

import cinema.cloud.service.film.api.Seance;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("seance-service")
public interface SeanceClient {

    @RequestMapping(value = "/film/{filmId}/seances", method = RequestMethod.GET)
    Iterable<Seance> getSeancesForFilms(@PathVariable("filmId") Integer filmId);

}
