package cinema.cloud.service.order.api;

import cinema.cloud.service.order.api.domain.Seance;
import cinema.cloud.service.order.api.domain.Seat;
import cinema.cloud.service.order.store.OrderSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderSession orderSession;

    public void saveSeanceWithFilm(Seance seance) {
        orderSession.setSeance(seance);
    }

    public void saveSeat(Seat seat) {
        orderSession.setSeat(seat);
    }
}
