package IController;

import DTO.*;
import enumerations.ReservationState;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.List;

@Local
public interface IReservationController {
  void saveReservation(DTReservation dtReservation);
  List<DTReservation> getReservationsBySchedule(Long id);
  List<DTavailableDate> unavailableDate(long idShedule);
  List<DTavailableDate> unavailableTime(String reservationDate, long idShcedule);
  int createReservation(DTReservation dtReservation);
  DTReservation getByIdReservation(Long id);
  List<DTReservation> getReservations();
  void deleteReservation(Long id);
  List<DTReservationSend> getReservationsSend(Long idShcedule);
  int createReservation2(DTReservation dtReservation);
  List<DTReservationView> getReservationData(String ci);
  boolean reservationChangeState(Long idReservation, ReservationState reservationState);

  public List<DTReservationSend> getReservationsSend2(Long idShcedule);

  DTVaccinationPost assignReservationToBestPost(Long centerId);

  public void reservationToConfirmed(Long reservationId);

  public List<DTPackage> dtPackageFromReservation(Long id);

  public DTVaccinationPost dtVaccinationPostFromReservation(Long id);

  public DTCitizen dtCitizenFromReservation(Long id);

  List<DTCitizenToken> getCitizenTokenByReservation();

  DtMonitorDate getCountWeek(LocalDate startDate, LocalDate endDate, Long idVPlan);
}
