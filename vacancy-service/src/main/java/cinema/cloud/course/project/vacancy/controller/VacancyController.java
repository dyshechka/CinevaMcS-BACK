package cinema.cloud.course.project.vacancy.controller;

import cinema.cloud.course.project.vacancy.domain.Vacancy;
import cinema.cloud.course.project.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacancyController {

    @Autowired
    private VacancyRepository vacancyRepository;

    @GetMapping(value = "/vacancies")
    public Iterable<Vacancy> test() {
        return vacancyRepository.findAll();
    }

}
