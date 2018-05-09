package cinema.cloud.service.film.controller;

import cinema.cloud.service.film.domain.Film;
import cinema.cloud.service.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public ArrayList<Film> getAllFilms(@RequestParam("dateTime") Long dateTime) {
        if (dateTime == null) {
            return filmService.getAllFilms();
        } else {
            return filmService.getFilmsByDate(dateTime);
        }
    }

}