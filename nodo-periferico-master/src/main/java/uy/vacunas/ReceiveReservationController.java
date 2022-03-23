package uy.vacunas;

import Controllers.ReservationController;
import Entities.Reservation;
import Interfaces.IReservationController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.ejb.EJB;
import javax.inject.Inject;

@Controller
public class ReceiveReservationController {

    @Value("${vaccinatorCenterID}")
    Long id;

    private Environment env;

   // @Inject
    IReservationController reservationController = new ReservationController();

    @PostMapping("/receiveReservations")
    public String receiveReservations() {
    //String prop = env.getProperty("vaccinatorCenterID");
    //Long vID = Long.parseLong(prop);
        reservationController.receiveReservation(id);
        reservationController.receiveAssignations(id);
        return "redirect:/listReservations";
    }

    @PostMapping("/vaccinationAct")
    public String vaccinationAct(@RequestParam(name = "idReservation") Long id) {

        reservationController.vaccinationAct(id);
        return "redirect:/listReservations";
    }

    @PostMapping("/sendReservations")
    public String sendReservations() {

        reservationController.sendConfirmed();
        return "redirect:/listReservations";
    }

    @PostMapping("/return")
    public String goBack() {
       return "redirect:/listReservations";
    }

}
