package cinema.cloud.service.order.service;

import cinema.cloud.service.order.api.domain.Film;
import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import cinema.cloud.service.order.api.response.OrderResponse;
import cinema.cloud.service.order.client.FilmServiceClient;
import cinema.cloud.service.order.domain.Ticket;
import cinema.cloud.service.order.domain.TicketOrder;
import cinema.cloud.service.order.repository.OrderRepository;
import cinema.cloud.service.order.repository.TicketRepository;
import cinema.cloud.service.order.store.OrderSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderSession orderSession;

    @Autowired
    private FilmServiceClient filmServiceClient;

    private static final BigDecimal COST_OF_TICKET = new BigDecimal(50);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public void saveSeanceWithFilm(Seance seance) {
        orderSession.setSeance(seance);
    }

    public boolean saveSeat(List<Seat> seats) {
        Seance seance = orderSession.getSeance();
        ArrayList<Integer> seatIds = seats.stream().map(Seat::getId).collect(Collectors.toCollection(ArrayList::new));
        List<Ticket> ticketsByIdsAndAndSeanceTime = ticketRepository.getTicketsByIdsAndAndSeanceTime(seatIds, seance.getTime(), seance.getHallId());
        if (!ticketsByIdsAndAndSeanceTime.isEmpty()) {
            return false;
        }
        orderSession.setSeats(seats);
        return true;
    }

    @Transactional
    public OrderResponse calculateOrderCost() {
        Seance seance = orderSession.getSeance();
        List<Seat> seats = orderSession.getSeats();
        DateTime time = new DateTime(seance.getTime());
        Film film = filmServiceClient.getFilmById(seance.getFilmId());
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
                    ticket.setCost(ticketCost);
                    ticket.setSeatId(seat.getId());
                    ticket.setSeanceTime(seance.getTime());
                    ticket.setHallId(seance.getHallId());
                    ticket.setDate(Calendar.getInstance().getTime());
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
            commonCost = commonCost.add(ticket.getCost());
        }

        OrderResponse response = new OrderResponse(tickets, commonCost);
        orderSession.setOrderResponse(response);
        return response;
    }

    @Transactional
    public OrderResponse saveOrder() {
        OrderResponse orderResponse = orderSession.getOrderResponse();
        TicketOrder order = new TicketOrder();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setCost(orderResponse.getCommonCost());
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setTickets(orderResponse.getTickets());
        order.getTickets().forEach(ticket -> ticket.setOrder(order));
        order.setUsername(username);
        TicketOrder savedOrder = orderRepository.save(order);
        orderResponse.setOrderId(savedOrder.getId());
        return orderResponse;
    }
}
