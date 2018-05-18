package cinema.cloud.service.hall.service;

import cinema.cloud.service.hall.api.HallWithRows;
import cinema.cloud.service.hall.api.RowWithSeats;
import cinema.cloud.service.hall.client.OrderClient;
import cinema.cloud.service.hall.domain.Seat;
import cinema.cloud.service.hall.repository.HallRepository;
import cinema.cloud.service.hall.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private OrderClient orderClient;

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public HallWithRows getSeatsByHallId(Integer hallId, Integer seanceId) {
        List<Integer> blockedSeatIdsForSeance = orderClient.getBlockedSeatIdsForSeance(seanceId);
        List<Seat> seatsByHallId = seatRepository.getSeatsByHallId(hallId);
        List<RowWithSeats> rowsWithSeats = new ArrayList<>();

        ArrayList<Integer> rows = seatsByHallId.stream()
                .filter(distinctByKey(Seat::getRow))
                .map(Seat::getRow)
                .collect(Collectors.toCollection(ArrayList::new));

        rows.forEach(row -> {
            ArrayList<Seat> seatsInRow = seatsByHallId.stream()
                    .peek(seat -> seat.setFree(!blockedSeatIdsForSeance.contains(seat.getId())))
                    .filter(seat -> row.equals(seat.getRow()))
                    .collect(Collectors.toCollection(ArrayList::new));
            rowsWithSeats.add(new RowWithSeats(row, seatsInRow));
        });

        return new HallWithRows(hallRepository.findOne(hallId).getName(), rowsWithSeats);
    }

    public Iterable<Seat> getSeatsByIds(List<Integer> seatIds) {
        return seatRepository.findAll(seatIds);
    }

}
