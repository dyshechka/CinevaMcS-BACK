package cinema.cloud.service.hall.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-service")
public interface OrderClient {

//    @RequestMapping(value = "/film/{filmId}/seances", method = RequestMethod.GET)
//    Iterable<Seance> getSeancesForFilms(@PathVariable("filmId") Integer filmId);

}
