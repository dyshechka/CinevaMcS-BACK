package cinema.cloud.service.order.controller;

import cinema.cloud.service.order.api.request.CalculateOrderRequest;
import cinema.cloud.service.order.api.response.CinemaOrder;
import cinema.cloud.service.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/calculate")
    public ResponseEntity<CinemaOrder> calculate(@RequestBody CalculateOrderRequest request) {
        return new ResponseEntity(orderService.calculateOrderCost(request.getSeance(), request.getSeats()), HttpStatus.OK);
    }

    @PostMapping(value = "/approve")
    public ResponseEntity<CinemaOrder> approve(@RequestBody CinemaOrder cinemaOrder) {
        return new ResponseEntity(orderService.saveOrder(cinemaOrder), HttpStatus.OK);
    }
}
