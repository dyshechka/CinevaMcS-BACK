package cinema.cloud.service.hall.service;

import cinema.cloud.service.hall.domain.Hall;
import cinema.cloud.service.hall.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public Iterable<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

}
