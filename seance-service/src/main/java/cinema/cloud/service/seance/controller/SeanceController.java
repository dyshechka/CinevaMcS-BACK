package cinema.cloud.service.seance.controller;

import cinema.cloud.service.seance.domain.Seance;
import cinema.cloud.service.seance.repository.SeanceRepository;
import cinema.cloud.service.seance.request.GetSeancesByDateAndHallRequest;
import cinema.cloud.service.seance.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    @Autowired
    private SeanceRepository seanceRepository;

    @GetMapping(value = "/film/{id}/seances")
    public List<Seance> getSeancesByFilmId(@PathVariable Integer id) {
        return seanceRepository.getSeancesByFilmId(id);
    }

    @PostMapping(value = "/seancesByDateAndHall")
    public Iterable<Seance> getSeancesForDateAndHall(@RequestBody GetSeancesByDateAndHallRequest request) {
        return seanceService.getSeancesForDateAndHallId(request);
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
}
