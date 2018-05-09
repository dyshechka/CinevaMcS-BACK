package cinema.cloud.service.film.service;

import cinema.cloud.service.film.domain.Film;
import cinema.cloud.service.film.repository.FilmRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    @Transactional
    public ArrayList<Film> getAllFilms() {
        Iterable<Film> all = repository.findAll();
        Assert.notNull(all, "Null result from DB");
        return Lists.newArrayList(repository.findAll());
    }

    @Transactional
    public ArrayList<Film> getFilmsByDate(Long dateTime) {
        Iterable<Film> all = repository.findAll();
        Assert.notNull(all, "Null result from DB");
        ArrayList<Film> films = Lists.newArrayList(repository.findAll());
        return films.stream()
                .filter(film -> film.getRentalPeriod().getDateBegin().isBefore(dateTime))
                .filter(film -> film.getRentalPeriod().getDateEnd().isAfter(dateTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
