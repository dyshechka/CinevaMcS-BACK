package cinema.cloud.course.project.seance.service;

import cinema.cloud.course.project.seance.api.Film;
import cinema.cloud.course.project.seance.api.FreeTimeInterval;
import cinema.cloud.course.project.seance.client.FilmClient;
import cinema.cloud.course.project.seance.domain.Seance;
import cinema.cloud.course.project.seance.repository.SeanceRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private FilmClient filmClient;

    @Transactional
    public Iterable<Seance> getSeancesForDateAndHallId(Long date, Integer hallId) {
        Date time = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
        Date start = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
        Date finish = calendar.getTime();
        Iterable<Seance> result = seanceRepository.getSeanceByTimeBetweenAndHallId(start, finish, hallId);
        return StreamSupport.stream(result.spliterator(), false)
                .sorted(Comparator.comparing(Seance::getTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public List<FreeTimeInterval> getFreeTimeByDayAndHall(Long date, Integer hallId, Integer filmId) {
        Date time = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
        Date start = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
        Date finish = calendar.getTime();

        // Get all data
        List<Seance> seances = StreamSupport.stream(
                seanceRepository.getSeanceByTimeBetweenAndHallId(start, finish, hallId).spliterator(), false)
                .sorted(Comparator.comparing(Seance::getTime))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> filmsIds = seances.stream().map(Seance::getFilmId).collect(Collectors.toCollection(ArrayList::new));

        List<Film> filmsByIds = filmClient.getFilmsByIds(filmsIds);

        Map<Integer, Integer> filmsDurations = new HashMap<>();
        filmsByIds.forEach(film -> filmsDurations.put(film.getId(), film.getDuration()));

        // Damn magic
        DateTime genesisDateTime = new DateTime(date);
        calendar.set(genesisDateTime.getYear(), genesisDateTime.getMonthOfYear() - 1, genesisDateTime.getDayOfMonth(), 11, 0);
        long genesisLeft = calendar.getTime().getTime();
        calendar.set(genesisDateTime.getYear(), genesisDateTime.getMonthOfYear() - 1, genesisDateTime.getDayOfMonth(), 23, 59);
        long maxDayTime = calendar.getTime().getTime();

        // if incoming time < 11:00, then genesisLeft = 11:00
        if (date < genesisLeft) {
            date = genesisLeft;
        }

        List<FreeTimeInterval> freeIntervals = new ArrayList<>();
        long maxRightBorder = date;

        while (maxRightBorder < maxDayTime) {
            FreeTimeInterval interval = getInterval(maxRightBorder, seances, filmsDurations);
            if (interval.getStart() == null) {
                maxRightBorder = interval.getFinish();
            } else {
                freeIntervals.add(interval);
                maxRightBorder = interval.getFinish();
            }
        }

        List<Film> filmsByDate = filmClient.getFilmsByDate(date);
        Map<Integer, Integer> filmsByDateDurations = new HashMap<>();
        filmsByDate.forEach(film -> filmsByDateDurations.put(film.getId(), film.getDuration()));
        Integer filmDuration = filmsByDateDurations.get(filmId);
        List<FreeTimeInterval> specifiedTimeIntervals = new ArrayList<>();

        for (FreeTimeInterval freeInterval : freeIntervals) {
            Long begin = freeInterval.getStart();
            while (begin < freeInterval.getFinish()) {
                FreeTimeInterval freeTimeInterval = new FreeTimeInterval();
                freeTimeInterval.setStart(begin);
                long end = begin + filmDuration * 60000 + 1200000;
                if (end > freeInterval.getFinish()) {
                    break;
                }
                freeTimeInterval.setFinish(end);
                begin = end;
                specifiedTimeIntervals.add(freeTimeInterval);
            }
        }

        return specifiedTimeIntervals;
    }

    private FreeTimeInterval getInterval(long startTime, List<Seance> seances, Map<Integer, Integer> filmsDurations) {
        Date startDate = new Date(startTime);

        ArrayList<Seance> seanceForCurrentStartTime = seances.stream().filter(seance -> seance.getTime().getTime() == startTime)
                .collect(Collectors.toCollection(ArrayList::new));

        if (!seanceForCurrentStartTime.isEmpty()) {
            Seance seance = seanceForCurrentStartTime.get(0);
            // 1200000 - tech pause (20 min)
            return new FreeTimeInterval(null, seance.getTime().getTime()
                    + filmsDurations.get(seance.getFilmId()) * 60000 + 1200000);
        }

        FreeTimeInterval freeTimeInterval = new FreeTimeInterval();
        Optional<Seance> first = seances.stream().filter(seance -> seance.getTime().after(startDate)).findFirst();
        first.ifPresent(seance -> {
            freeTimeInterval.setStart(startDate.getTime());
            freeTimeInterval.setFinish(seance.getTime().getTime());
        });
        if (freeTimeInterval.getStart() == null) {
            Calendar calendar = Calendar.getInstance();
            DateTime genesisDateTime = new DateTime(startTime);
            calendar.set(genesisDateTime.getYear(), genesisDateTime.getMonthOfYear() - 1, genesisDateTime.getDayOfMonth(), 23, 59);
            freeTimeInterval.setStart(startTime);
            freeTimeInterval.setFinish(calendar.getTime().getTime());
        }

        return freeTimeInterval;
    }

    @Transactional
    public void saveSeanceList(List<Seance> seances) {
        seanceRepository.save(seances);
    }

    @Transactional
    public Seance getSeanceById(Integer id) {
        return seanceRepository.findOne(id);
    }
}
