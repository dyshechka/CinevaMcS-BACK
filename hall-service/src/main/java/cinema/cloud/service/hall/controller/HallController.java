package cinema.cloud.service.hall.controller;

import cinema.cloud.service.hall.api.HallWithRows;
import cinema.cloud.service.hall.domain.Hall;
import cinema.cloud.service.hall.domain.Seat;
import cinema.cloud.service.hall.service.HallService;
import cinema.cloud.service.hall.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @GetMapping(value = "/halls")
    public Iterable<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping(value = "/seats")
    public HallWithRows getSeatsForHall(@RequestParam Integer hallId, @RequestParam Integer seanceId) {
        return seatService.getSeatsByHallId(hallId, seanceId);
    }

    @PostMapping(value = "/seatsByIds")
    public Iterable<Seat> getSeatsByIds(@RequestBody List<Integer> seatIds) {
        return seatService.getSeatsByIds(seatIds);
    }
}
