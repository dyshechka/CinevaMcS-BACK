package cinema.cloud.service.order.client;

import cinema.cloud.service.order.api.domain.Film;
import cinema.cloud.service.order.api.domain.Seat;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("hall-service")
public interface HallServiceClient {

    @GetMapping(value = "/hall")
    Resources<Film> getAllFilms();

    @PostMapping(value = "/seatsByIds")
    List<Seat> getSeats(List<Integer> seatsIds);

}
