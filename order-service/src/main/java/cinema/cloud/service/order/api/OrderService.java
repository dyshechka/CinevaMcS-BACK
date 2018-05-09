package cinema.cloud.service.order.api;

import cinema.cloud.service.order.client.FilmServiceClient;
import cinema.cloud.service.order.store.OrderSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderSession orderSession;

    @Autowired
    private FilmServiceClient filmServiceClient;

    public boolean selectFilm() {
//        GettingFilmResponse film = filmServiceClient.getAllFilms();

//        boolean result = film != null;
//        if (result) {
//            orderSession.setFilm(film);
//        }
        return false;
    }

//    public List<Seance> selectSeance() {
//        GettingFilmResponse film = orderSession.getFilm();
//        if (responseEntity == null) {
//            return null;
//        }
//        return Arrays.asList(responseEntity);
//    }
//
//    public Seance getSeance(Integer seanceId) {
//        RestTemplate template = new RestTemplate();
//        Seance seance = template.getForObject(String.format("%s/seance/%s", MCS_SEANCE_URL, seanceId), Seance.class);
//        boolean result = seance != null;
//        if (result) {
//            orderSession.setSeance(seance);
//        }
//        return seance;
//    }
//
//    public List<Seat> getSeats() {
//        Seance seance = orderSession.getSeance();
//        RestTemplate template = new RestTemplate();
//        Seat[] seatsArray = template.getForObject(String.format("%s/hall/%s/seats", MCS_HALL_URL, seance.getHallId()), Seat[].class);
//        return Arrays.asList(seatsArray);
//    }
//
//    public Seat selectSeat(Integer seatId) {
//        RestTemplate template = new RestTemplate();
//        Seat seat = template.getForObject(String.format("%s/seat/%s", MCS_HALL_URL, seatId), Seat.class);
//        boolean result = seat != null;
//        if (result) {
//            orderSession.setSeat(seat);
//        }
//        return seat;
//    }
//
//    public void getApprove() {
//        ApproveResponse approveResponse = new ApproveResponse();
//        GettingFilmResponse film = orderSession.getFilm();
//        Map<String, String> filmData = new HashMap<>();
//        filmData.put("filmName", film.getName());
//        filmData.put("filmAgeRestrictions", film.getAgeRestrictions().toString());
//        filmData.put("filmGenres", film.getGenres().toString());
//        approveResponse.setFilmData(filmData);
//        Seance seance = orderSession.getSeance();
//        Map<String, String> seanceData = new HashMap<>();
//        seanceData.put("seanceDate", seance.getTime().toString());
//    }
}
