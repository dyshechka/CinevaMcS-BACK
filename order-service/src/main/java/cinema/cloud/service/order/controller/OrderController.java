package cinema.cloud.service.order.controller;

import cinema.cloud.service.order.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/step/first", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getFirstStep() {
        return new ResponseEntity(orderService.selectFilm() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @RequestMapping(value = "/step/second", method = RequestMethod.GET)
//    public ResponseEntity<List<Seance>> getSecondStep() {
//        List<Seance> seanceList = orderService.selectSeance();
//        return new ResponseEntity<>(seanceList, seanceList == null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @RequestMapping(value = "/step/second", method = RequestMethod.POST)
//    public ResponseEntity applySecondStep(@RequestParam @NotNull Integer seanceId) {
//        Seance seance = orderService.getSeance(seanceId);
//        return new ResponseEntity<>(seance, seance == null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @RequestMapping(value = "/step/third", method = RequestMethod.GET)
//    public ResponseEntity<List<Seat>> getThirdStep() {
//        List<Seat> seats = orderService.getSeats();
//        return new ResponseEntity<>(seats, seats == null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @RequestMapping(value = "/step/third", method = RequestMethod.POST)
//    public ResponseEntity applyThirdStep(@RequestParam @NotNull Integer seatId) {
//        return new ResponseEntity(orderService.selectSeat(seatId) == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
//    }

}
