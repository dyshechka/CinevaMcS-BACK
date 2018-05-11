package cinema.cloud.service.seance.controller;

import cinema.cloud.service.seance.domain.Seance;
import cinema.cloud.service.seance.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeanceController {

    @Autowired
    private SeanceRepository seanceRepository;

    @ResponseBody
    @GetMapping(value = "/film/{id}/seances", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Seance> getSeancesByFilmId(@PathVariable Integer id) {
        return seanceRepository.getSeancesByFilmId(id);
    }

}
