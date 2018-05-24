package cinema.cloud.course.project.service.order.service;

import cinema.cloud.course.project.service.order.api.domain.Film;
import cinema.cloud.course.project.service.order.api.domain.Seance;
import cinema.cloud.course.project.service.order.api.domain.Seat;
import cinema.cloud.course.project.service.order.api.response.CinemaOrder;
import cinema.cloud.course.project.service.order.client.FilmServiceClient;
import cinema.cloud.course.project.service.order.client.HallServiceClient;
import cinema.cloud.course.project.service.order.client.SeanceClient;
import cinema.cloud.course.project.service.order.domain.Ticket;
import cinema.cloud.course.project.service.order.domain.TicketOrder;
import cinema.cloud.course.project.service.order.repository.OrderRepository;
import cinema.cloud.course.project.service.order.repository.TicketRepository;
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
    private FilmServiceClient filmServiceClient;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private HallServiceClient hallServiceClient;
    @Autowired
    private SeanceClient seanceClient;

    private static final BigDecimal COST_OF_TICKET = new BigDecimal(50);

    @Transactional
    public List<Integer> getBlockedSeatIdsForSeance(Integer seanceId) {
        List<Ticket> ticketsBySeanceAndIds = ticketRepository.getTicketsBySeanceId(seanceId);
        List<Integer> blockedSeatIds = new ArrayList<>();
        ticketsBySeanceAndIds.forEach(ticket -> blockedSeatIds.add(ticket.getSeatId()));
        return blockedSeatIds;
    }

    @Transactional
    public CinemaOrder calculateOrderCost(Integer seanceId, List<Integer> seatsIds) {
        Seance seance = seanceClient.getSeanceById(seanceId);
        List<Seat> seats = hallServiceClient.getSeats(seatsIds);
        Film film = filmServiceClient.getFilmById(seance.getFilmId());
        DateTime time = new DateTime(seance.getTime());
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
                    ticket.setSeanceId(seance.getId());
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

        return new CinemaOrder(tickets, commonCost);
    }

    @Transactional
    public CinemaOrder saveOrder(CinemaOrder cinemaOrder) {
        TicketOrder order = new TicketOrder();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setCost(cinemaOrder.getCommonCost());
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setTickets(cinemaOrder.getTickets());
        order.getTickets().forEach(ticket -> ticket.setOrder(order));
        order.setUsername(username);
        TicketOrder savedOrder = orderRepository.save(order);
        cinemaOrder.setOrderId(savedOrder.getId());
        cinemaOrder.setOrderDate(savedOrder.getOrderDate());
        return cinemaOrder;
    }

    @Transactional
    public CinemaOrder getMockOrder() {
        TicketOrder one = orderRepository.findOne(2);
        CinemaOrder cinemaOrder = new CinemaOrder();
        cinemaOrder.setOrderId(one.getId());
        cinemaOrder.setTickets(one.getTickets());
        cinemaOrder.setCommonCost(one.getCost());
        cinemaOrder.setOrderDate(one.getOrderDate());
        return cinemaOrder;
    }

    @Transactional
    public List<CinemaOrder> getUserOrders() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TicketOrder> ticketOrderByUsername = orderRepository.getTicketOrderByUsername(username);
        List<CinemaOrder> cinemaOrders = new ArrayList<>();
        ticketOrderByUsername.forEach(ticketOrder -> {
            CinemaOrder cinemaOrder = new CinemaOrder();
            cinemaOrder.setOrderId(ticketOrder.getId());
            cinemaOrder.setTickets(ticketOrder.getTickets());
            cinemaOrder.setCommonCost(ticketOrder.getCost());
            cinemaOrder.setOrderDate(ticketOrder.getOrderDate());
            cinemaOrders.add(cinemaOrder);
        });

        return cinemaOrders;
    }
}
