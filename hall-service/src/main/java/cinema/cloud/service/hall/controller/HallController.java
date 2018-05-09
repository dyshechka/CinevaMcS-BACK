package cinema.cloud.service.hall.controller;

import cinema.cloud.service.hall.domain.Hall;
import cinema.cloud.service.hall.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HallController {

    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/halls", method = RequestMethod.GET)
    public Iterable<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

}
