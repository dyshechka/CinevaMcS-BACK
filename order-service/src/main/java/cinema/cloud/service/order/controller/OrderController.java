package cinema.cloud.service.order.controller;

import cinema.cloud.service.order.api.OrderService;
import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/create")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/step/first", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity applyFirstStep(Seance seance) {
        orderService.saveSeanceWithFilm(seance);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/step/second", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity applySecondStep(Seat seat) {
        orderService.saveSeat(seat);
        return new ResponseEntity(HttpStatus.OK);
    }
}
