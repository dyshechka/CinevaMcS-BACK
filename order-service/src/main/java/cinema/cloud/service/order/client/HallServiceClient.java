package cinema.cloud.service.order.client;

import cinema.cloud.service.order.api.domain.Film;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("hall-service")
public interface HallServiceClient {

    @RequestMapping(value = "/hall", method = RequestMethod.GET)
    Resources<Film> getAllFilms();

}
