package cinema.cloud.service.seance.service;

import cinema.cloud.service.seance.domain.Seance;
import cinema.cloud.service.seance.repository.SeanceRepository;
import cinema.cloud.service.seance.request.GetSeancesByDateAndHallRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Transactional
    public Iterable<Seance> getSeancesForDateAndHallId(GetSeancesByDateAndHallRequest request) {
        Date time = request.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
        Date start = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
        Date finish = calendar.getTime();
        return seanceRepository.getSeanceByTimeBetweenAndHallId(start, finish, request.getHallId());
    }

    @Transactional
    public void saveSeanceList(List<Seance> seances) {
        seanceRepository.save(seances);
    }
}
