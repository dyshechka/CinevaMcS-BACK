package cinema.cloud.course.project.service.hall.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("order-service")
public interface OrderClient {

    @RequestMapping(value = "/order/seanceTickets/{seanceId}", method = RequestMethod.GET)
    List<Integer> getBlockedSeatIdsForSeance(@PathVariable("seanceId") Integer seanceId);

}
