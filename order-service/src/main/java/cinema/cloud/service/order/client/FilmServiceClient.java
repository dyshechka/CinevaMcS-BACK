package cinema.cloud.service.order.client;

import cinema.cloud.service.order.api.domain.Film;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("film-service")
public interface FilmServiceClient {

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    Iterable<Film> getAllFilms();

}
