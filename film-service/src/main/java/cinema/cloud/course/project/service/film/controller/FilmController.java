package cinema.cloud.course.project.service.film.controller;

import cinema.cloud.course.project.service.film.api.FilmWithSeance;
import cinema.cloud.course.project.service.film.domain.Film;
import cinema.cloud.course.project.service.film.repository.FilmRepository;
import cinema.cloud.course.project.service.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private FilmRepository filmRepository;

    static private final List<Integer> mostExpectedFilmIds = Collections.unmodifiableList(Arrays.asList(1, 2));

    @GetMapping(path = "/films")
    public List<FilmWithSeance> getAllFilms(@RequestParam(value = "dateTime", required = false) Long dateTime) {
        return filmService.getFilmsWithSeances(dateTime);
    }

    @GetMapping(path = "/filmsByDate")
    public List<Film> getFilmsByDate(@RequestParam(value = "dateTime", required = false) Long dateTime) {
        return filmService.getFilmsByDate(dateTime);
    }

    @GetMapping(path = "/film/{id}")
    public Film getFilmById(@PathVariable("id") Integer id) {
        return filmRepository.getFilmsById(id);
    }

    @PostMapping(path = "/filmByIds")
    public Iterable<Film> getFilms(@RequestBody List<Integer> ids) {
        return filmRepository.findAll(ids);
    }

    @GetMapping(path = "/getMostExpectedFilms")
    public Iterable<Film> getMostExpectedFilms() {
        return filmRepository.findAll(mostExpectedFilmIds);
    }

    @GetMapping(path = "/getImg")
    public ResponseEntity getPoster(@RequestParam Integer filmId) {
        ClassPathResource imgFile = new ClassPathResource("img/" + String.valueOf(filmId) + ".png");

        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        } catch (IOException e) {
            ClassPathResource stub = new ClassPathResource("img/placeholder.png");
            try {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(new InputStreamResource(stub.getInputStream()));
            } catch (IOException ex) {
                return (ResponseEntity) ResponseEntity.badRequest();
            }
        }
    }
}
