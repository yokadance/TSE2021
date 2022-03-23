package IDAL;

import DTO.*;
import enumerations.ReservationState;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.List;

@Local
public interface IReservationData {
  void saveReservation(DTReservation dtReservation);
  int createReservation(
    DTReservation dtReservation,
    DTSchedule dtSchedule,
    DTCitizen dtCitizen,
    DTVaccinationCenter dtVaccinationCenter,
    DTVaccinationPost dtVaccinationPost
  );
  List<DTReservation> getReservations();
  DTReservation getByIdReservation(Long id);
  void deleteReservation(Long id);
  List<DTReservation> getReservationsBySchedule(Long id);
  boolean availableReservation(String reservationTime, String reservationDate, int fraction, Long schId, int postCount);
  List<DTavailableDate> unavailableDate(DTSchedule dtSchedule);
  List<DTavailableDate> unavailableTime(String reservationDate, long idShcedule, int quotasDaily);
  List<DTReservationSend> getReservationsSend(Long idShcedule);
  List<DTReservationView> getReservationData(String ci);
  boolean reservationChangeState(Long idReservation, ReservationState reservationState);
  DTVaccinationPost assignReservationToBestPost(Long centerId);

  public List<DTReservationSend> getReservationsSend2(Long idShcedule);

  public void reservationToConfirmed(Long idReservation);

  public List<DTPackage> dtPackageFromReservation(Long id);

  public DTVaccinationPost dtVaccinationPostFromReservation(Long id);

  public DTCitizen dtCitizenFromReservation(Long id);

  List<DTCitizenToken> getCitizenTokenByReservation();

  DtMonitorDate getCountWeek(LocalDate startDate, LocalDate endDate, Long idVPlan);
}
