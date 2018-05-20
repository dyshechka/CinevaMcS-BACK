package cinema.cloud.service.seance.controller;

import cinema.cloud.service.seance.api.FreeTimeInterval;
import cinema.cloud.service.seance.domain.Seance;
import cinema.cloud.service.seance.repository.SeanceRepository;
import cinema.cloud.service.seance.service.SeanceService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    @Autowired
    private SeanceRepository seanceRepository;

    @GetMapping(value = "/film/{id}/seances")
    public List<Seance> getSeancesByFilmId(@PathVariable Integer id, @RequestParam("date") Long date) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime(date);
        calendar.set(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 23, 59);
        return seanceRepository.getSeancesByFilmId(id, currentDate, calendar.getTime());
    }

    @GetMapping(value = "/seancesByDateAndHall")
    public Iterable<Seance> getSeancesForDateAndHall(@RequestParam Long date, Integer hallId) {
        return seanceService.getSeancesForDateAndHallId(date, hallId);
    }

    @PostMapping(value = "/saveSeanceList")
    public ResponseEntity saveSeanceList(@RequestBody List<Seance> seances) {
        seanceService.saveSeanceList(seances);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/seance/{id}")
    public Seance getSeanceById(@PathVariable Integer id) {
        return seanceService.getSeanceById(id);
    }

    @GetMapping(value = "/seance/freeTime")
    public List<FreeTimeInterval> getFreeTimeForDayAndHall(@RequestParam Long date, Integer hallId) {
        return seanceService.getFreeTimeByDayAndHall(date, hallId);
    }
}
