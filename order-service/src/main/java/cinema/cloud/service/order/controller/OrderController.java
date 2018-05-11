package cinema.cloud.service.order.controller;

import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import cinema.cloud.service.order.api.response.TicketsResponse;
import cinema.cloud.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order/create")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/step/first")
    public ResponseEntity applyFirstStep(@Valid @RequestBody Seance seance) {
        orderService.saveSeanceWithFilm(seance);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/step/second")
    public ResponseEntity applySecondStep(@RequestBody List<Seat> seats) {
        orderService.saveSeat(seats);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/step/third")
    public ResponseEntity<TicketsResponse> applyThirdStep() {
        return new ResponseEntity(orderService.calculateOrderCost(), HttpStatus.OK);
    }
}
