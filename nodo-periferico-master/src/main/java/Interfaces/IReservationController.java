package Interfaces;

import Entities.Reservation;
import Entities.Vaccinators;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface IReservationController {
    public void saveReservation(Reservation reservation);
    Document parseXmlFile(String in);
    public void receiveReservation(Long id);
    public List<Long> getConfirmed ();
    public void sendConfirmed ();
    public List<Reservation> getReservations();
    Reservation getReservationById(Long idReservation);
    public void vaccinationAct (Long idReservation);
    public void receiveAssignations(Long id);
    public List<Vaccinators> getVaccinators();
    public Vaccinators getVaccinatorById(int id);
}
