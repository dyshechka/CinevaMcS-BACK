package cinema.cloud.service.order.service;

import cinema.cloud.service.order.api.domain.Film;
import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import cinema.cloud.service.order.api.response.TicketsResponse;
import cinema.cloud.service.order.client.FilmServiceClient;
import cinema.cloud.service.order.domain.Ticket;
import cinema.cloud.service.order.store.OrderSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderSession orderSession;

    private static final BigDecimal COST_OF_TICKET = new BigDecimal(50);
    private static final int REMAINING_DAYS = 8;
    @Autowired
    private FilmServiceClient filmServiceClient;

    public void saveSeanceWithFilm(Seance seance) {
        orderSession.setSeance(seance);
    }

    public void saveSeat(List<Seat> seats) {
        orderSession.setSeats(seats);
    }

    public TicketsResponse calculateOrderCost() {
        Seance seance = orderSession.getSeance();
//        Seance seance = orderSession.getSeance();
        List<Seat> seats = orderSession.getSeats();
//        Seat seatTest = new Seat();
//        seatTest.setVip(Boolean.TRUE);
//        List<Seat> seats = Collections.singletonList(seatTest);
        DateTime time = new DateTime(seance.getTime());
//        DateTime time = DateTime.now();
        Film film = filmServiceClient.getFilmById(seance.getFilmId());
//        Film film = filmServiceClient.getFilmById(1);
        // Count of seats cost
        DateTime dateBegin1 = film.getRentalPeriod().getDateBegin();
        DateTime dateBegin2 = film.getRentalPeriod().getDateEnd();
        LocalDateTime dateEnd = LocalDateTime.of(dateBegin1.getYear(), dateBegin1.getMonthOfYear(), dateBegin1.getDayOfMonth(), dateBegin1.getHourOfDay(), dateBegin1.getMinuteOfDay());
        LocalDateTime dateBegin = LocalDateTime.of(dateBegin2.getYear(), dateBegin2.getMonthOfYear(), dateBegin2.getDayOfMonth(), dateBegin2.getHourOfDay(), dateBegin2.getMinuteOfDay());
        long quantityOfDays = ChronoUnit.DAYS.between(dateEnd, dateBegin);
        long remainingDays = ChronoUnit.DAYS.between(dateEnd, LocalDateTime.now());
        BigDecimal commonCost = BigDecimal.ZERO;
        ArrayList<Ticket> tickets = seats.stream()
                .map(seat -> {
                    BigDecimal baseTicketCost = COST_OF_TICKET.multiply(seat.getVip() ? BigDecimal.ONE : BigDecimal.valueOf(0.5));
                    BigDecimal seanceTimeCost = COST_OF_TICKET.multiply(time.getHourOfDay() >= 11 ? BigDecimal.valueOf(2) : BigDecimal.ONE);
                    BigDecimal doubleX = BigDecimal.valueOf(2)
                            .multiply(new BigDecimal(remainingDays))
                            .multiply(COST_OF_TICKET)
                            .divide(new BigDecimal(quantityOfDays), BigDecimal.ROUND_HALF_UP);
                    BigDecimal ticketCost = baseTicketCost.add(seanceTimeCost).add(doubleX);
                    Ticket ticket = new Ticket();
                    ticket.setPrice(ticketCost);
                    ticket.setSeat(seat);
                    ticket.setDate(LocalDateTime.now());
                    StringBuilder filmName = new StringBuilder();
                    filmName.append(film.getName());
                    filmName.append(" (");
                    filmName.append(film.getAgeRestrictions().get(0).getName());
                    filmName.append(")");
                    ticket.setFilm(filmName.toString());
                    return ticket;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        for (Ticket ticket : tickets) {
            commonCost = commonCost.add(ticket.getPrice());
        }

        TicketsResponse response = new TicketsResponse(tickets, commonCost);
        return response;
    }
}
