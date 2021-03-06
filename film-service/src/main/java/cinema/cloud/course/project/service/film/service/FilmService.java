package cinema.cloud.course.project.service.film.service;

import cinema.cloud.course.project.service.film.api.FilmWithSeance;
import cinema.cloud.course.project.service.film.api.Seance;
import cinema.cloud.course.project.service.film.client.SeanceClient;
import cinema.cloud.course.project.service.film.domain.*;
import cinema.cloud.course.project.service.film.repository.*;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    @Autowired
    private SeanceClient seanceClient;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RentalPeriodRepository rentalPeriodRepository;

    @Autowired
    private AgeRestrictionsRepository restrictionsRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GenreRepository genreRepository;

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
    public ArrayList<Seance> getSeancesForFilm(Integer filmId, Long targetTime) {
        Iterable<Seance> seancesForFilms = seanceClient.getSeancesForFilms(filmId, targetTime);
        Assert.notNull(seancesForFilms, "Null result from DB");
        return Lists.newArrayList(seancesForFilms);
    }

    @Transactional
    public ArrayList<FilmWithSeance> getFilmsWithSeances(Long dateTime) {
        DateTime incDate = new DateTime(dateTime);
        DateTime today = DateTime.now();
        ArrayList<FilmWithSeance> filmsWithSeances = new ArrayList<>();
        if (incDate.getDayOfMonth() == today.getDayOfMonth() && incDate.getMonthOfYear() == today.getMonthOfYear()) {
            // Today
            Calendar calendar = Calendar.getInstance();
            calendar.set(today.getYear(), today.getMonthOfYear() - 1, today.getDayOfMonth(), 23, 59);
            ArrayList<Film> filmsByDate = getFilmsByDate(dateTime);
            filmsByDate.forEach(film -> {
                ArrayList<Seance> seancesForFilm = getSeancesForFilm(film.getId(), dateTime);
                ArrayList<Seance> seances = seancesForFilm.stream().filter(seance ->
                        seance.getTime().after(new Date(dateTime)))
                        .filter(seance -> seance.getTime().before(calendar.getTime()))
                        .collect(Collectors.toCollection(ArrayList::new));
                if (!seances.isEmpty()) {
                    filmsWithSeances.add(new FilmWithSeance(film, seances));
                }
            });
        } else {
            Calendar calendar = Calendar.getInstance();
            DateTime targetTime = new DateTime(dateTime);
            calendar.set(targetTime.getYear(), targetTime.getMonthOfYear() - 1, targetTime.getDayOfMonth(), 23, 59);
            Date limit = calendar.getTime();
            calendar.set(targetTime.getYear(), targetTime.getMonthOfYear() - 1, targetTime.getDayOfMonth(), 0, 0);
            Date start = calendar.getTime();
            ArrayList<Film> filmsByDate = getFilmsByDate(dateTime);
            filmsByDate.forEach(film -> {
                ArrayList<Seance> seancesForFilm = getSeancesForFilm(film.getId(), dateTime);
                ArrayList<Seance> seances = seancesForFilm.stream().filter(seance ->
                        seance.getTime().after(start))
                        .filter(seance -> seance.getTime().before(limit))
                        .collect(Collectors.toCollection(ArrayList::new));
                if (!seances.isEmpty()) {
                    filmsWithSeances.add(new FilmWithSeance(film, seances));
                }
            });
        }

        return filmsWithSeances;
    }

    @Transactional
    public void saveFilm(Film film) {
        RentalPeriod rentalPeriod = rentalPeriodRepository.findOne(film.getRentalPeriod().getId());

        ArrayList<Integer> ageRestrictionIds = film.getAgeRestrictions().stream().map(AbstractDomain::getId).collect(Collectors.toCollection(ArrayList::new));
        Iterable<AgeRestriction> iterableAgeRestrictions = restrictionsRepository.findAll(ageRestrictionIds);
        List<AgeRestriction> ageRestrictions = StreamSupport.stream(iterableAgeRestrictions.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> genresIds = film.getGenres().stream().map(AbstractDomain::getId).collect(Collectors.toCollection(ArrayList::new));
        Iterable<Genre> iterableGenres = genreRepository.findAll(genresIds);
        List<Genre> genres = StreamSupport.stream(iterableGenres.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> countrieIds = film.getCountries().stream().map(AbstractDomain::getId).collect(Collectors.toCollection(ArrayList::new));
        Iterable<Country> iterableCountry = countryRepository.findAll(countrieIds);
        List<Country> countries = StreamSupport.stream(iterableCountry.spliterator(), false).collect(Collectors.toCollection(ArrayList::new));

        film.setCountries(countries);
        film.setAgeRestrictions(ageRestrictions);
        film.setRentalPeriod(rentalPeriod);
        film.setGenres(genres);

        filmRepository.save(film);
    }
}
