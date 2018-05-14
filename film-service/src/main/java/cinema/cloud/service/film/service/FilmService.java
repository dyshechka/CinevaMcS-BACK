package cinema.cloud.service.film.service;

import cinema.cloud.service.film.api.FilmWithSeance;
import cinema.cloud.service.film.api.Seance;
import cinema.cloud.service.film.client.SeanceClient;
import cinema.cloud.service.film.domain.Film;
import cinema.cloud.service.film.repository.FilmRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    @Autowired
    private SeanceClient seanceClient;

    @Transactional
    public ArrayList<Film> getFilmsByDate(Long dateTime) {
        Iterable<Film> all = repository.findAll();
        Assert.notNull(all, "Null result from DB");
        ArrayList<Film> films = Lists.newArrayList(all);
        return films.stream()
                .filter(film -> film.getRentalPeriod().getDateBegin().before(new Date(dateTime)))
                .filter(film -> film.getRentalPeriod().getDateEnd().after(new Date(dateTime)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public ArrayList<Seance> getSeancesForFilm(Integer filmId) {
        Iterable<Seance> seancesForFilms = seanceClient.getSeancesForFilms(filmId);
        Assert.notNull(seancesForFilms, "Null result from DB");
        return Lists.newArrayList(seancesForFilms);
    }

    @Transactional
    public ArrayList<FilmWithSeance> getFilmsWithSeances(Long dateTime) {
        ArrayList<FilmWithSeance> filmsWithSeances = new ArrayList<>();
        ArrayList<Film> filmsByDate = getFilmsByDate(dateTime);
        filmsByDate.forEach(film -> {
            filmsWithSeances.add(new FilmWithSeance(film, getSeancesForFilm(film.getId())));
        });

        return filmsWithSeances;
    }
}
