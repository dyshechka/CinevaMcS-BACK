package cinema.cloud.service.hall.service;

import cinema.cloud.service.hall.domain.Seat;
import cinema.cloud.service.hall.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getSeatsByHallId(Integer hallId) {
        return seatRepository.getSeatsByHallId(hallId);
    }

}
