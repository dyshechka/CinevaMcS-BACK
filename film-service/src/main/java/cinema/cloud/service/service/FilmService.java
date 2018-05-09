package cinema.cloud.service.service;

import cinema.cloud.service.domain.Film;
import cinema.cloud.service.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    public Iterable<Film> getAllFilms() {
        return repository.findAll();
    }

}
