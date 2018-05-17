package cinema.cloud.service.hall.controller;

import cinema.cloud.service.hall.api.HallWithRows;
import cinema.cloud.service.hall.api.RowWithSeats;
import cinema.cloud.service.hall.domain.Hall;
import cinema.cloud.service.hall.service.HallService;
import cinema.cloud.service.hall.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @RequestMapping(value = "/halls", method = RequestMethod.GET)
    public Iterable<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @RequestMapping(value = "/seats/{hallId}", method = RequestMethod.GET)
    public HallWithRows getSeatsForHall(@PathVariable("hallId") Integer hallId) {
        return seatService.getSeatsByHallId(hallId);
    }
}
