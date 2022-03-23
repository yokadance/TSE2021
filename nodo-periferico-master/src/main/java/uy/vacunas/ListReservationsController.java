package uy.vacunas;

import Controllers.ReservationController;
import Entities.Reservation;
import Entities.Vaccinators;
import Interfaces.IReservationController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListReservationsController {

    @Value("${vaccinationName}")
    String vaccinationName;

    @GetMapping("/listReservations")
    public String listReservations(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        IReservationController reservationController = new ReservationController();
        List<Reservation> reservationList = reservationController.getReservations();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("vaccinationName", vaccinationName);

        return "listReservations";
    }

    @PostMapping("/vaccinators")
    public String listVaccinators(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        IReservationController reservationController = new ReservationController();
        List<Vaccinators> vaccinatorsList = reservationController.getVaccinators();
        model.addAttribute("vaccinators", vaccinatorsList);
        model.addAttribute("vaccinationName", vaccinationName);
        return "vaccinators";
    }

}
