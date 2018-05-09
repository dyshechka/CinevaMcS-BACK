package cinema.cloud.service.controller;

import cinema.cloud.service.domain.Film;
import cinema.cloud.service.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public Iterable<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

}
