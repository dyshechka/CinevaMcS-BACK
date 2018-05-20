package cinema.cloud.service.film.controller;

import cinema.cloud.service.film.api.FilmWithSeance;
import cinema.cloud.service.film.domain.Film;
import cinema.cloud.service.film.repository.FilmRepository;
import cinema.cloud.service.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(path = "/films")
    public ArrayList<FilmWithSeance> getAllFilms(@RequestParam(value = "dateTime", required = false) Long dateTime) {
        return filmService.getFilmsWithSeances(dateTime);
    }

    @GetMapping(path = "/film/{id}")
    public Film getFilmById(@PathVariable("id") Integer id) {
        return filmRepository.getFilmsById(id);
    }

    @PostMapping(path = "/filmByIds")
    public Iterable<Film> getFilms(@RequestBody List<Integer> ids) {
        return filmRepository.findAll(ids);
    }
}
