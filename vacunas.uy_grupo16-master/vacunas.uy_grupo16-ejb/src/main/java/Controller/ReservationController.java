package Controller;

import DTO.*;
import IController.ICitizenController;
import IController.IReservationController;
import IDAL.IReservationData;
import IDAL.IScheduleData;
import IDAL.IVaccinationCenterData;
import enumerations.ReservationState;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Stateless
@LocalBean
public class ReservationController implements IReservationController {

  @EJB
  IReservationData iReservationData;

  @EJB
  IScheduleData iScheduleData;

  @EJB
  IVaccinationCenterData iVaccinationCenterData;

  @Inject
  ICitizenController iCitizenController;

  @Override
  public void saveReservation(DTReservation dtReservation) {
    iReservationData.saveReservation(dtReservation);
  }

  @Override
  public List<DTReservation> getReservationsBySchedule(Long id) {
    return iReservationData.getReservationsBySchedule(id);
  }

  @Override
  public DTReservation getByIdReservation(Long id) {
    return iReservationData.getByIdReservation(id);
  }

  @Override
  public List<DTReservation> getReservations() {
    return iReservationData.getReservations();
  }

  @Override
  public void deleteReservation(Long id) {
    iReservationData.deleteReservation(id);
  }

  @Override
  public List<DTavailableDate> unavailableDate(long idShedule) {
    DTSchedule dtSchedule = iScheduleData.getScheduleById(idShedule);
    return iReservationData.unavailableDate(dtSchedule);
  }

  @Override
  public List<DTavailableDate> unavailableTime(String reservationDate, long idShcedule) {
    DTSchedule dtSchedule = iScheduleData.getScheduleById(idShcedule);
    int quotasDaily = dtSchedule.getFraction() * 2;
    return iReservationData.unavailableTime(reservationDate, idShcedule, quotasDaily);
  }

  @Override
  public List<DTReservationSend> getReservationsSend(Long idShcedule) {
    return iReservationData.getReservationsSend(idShcedule);
  }

  @Override
  public List<DTReservationSend> getReservationsSend2(Long idShcedule) {
    return iReservationData.getReservationsSend2(idShcedule);
  }

  @Override
  public int createReservation(DTReservation dtReservation) {
    DTSchedule dtSchedule = iScheduleData.getScheduleById(Long.parseLong(dtReservation.getSchedule()));
    List<DTReservation> reservationList = iReservationData.getReservationsBySchedule(dtSchedule.getId());
    LocalTime reservationt = LocalTime.parse((String) dtSchedule.getStartTimeDaily());
    LocalDate reservationd = LocalDate.parse((String) dtSchedule.getStartDate());
    LocalTime endt = LocalTime.parse((String) dtSchedule.getEndTimeDaily());
    LocalDate endd = LocalDate.parse((String) dtSchedule.getEndDate());
    // int quotasDaily = Duration.between(reservationt, endt).toMinutes()*2;

    int quotas = dtSchedule.getFraction() * 2;
    //long quotasdaily = (d / dtSchedule.getFraction()) * quotas;
    System.out.println(reservationList.size());
    // iReservationData.unavailableDate(dtSchedule.getEndDate(),dtSchedule.getStartDate(),quotasDaily);
    if (
      iReservationData.availableReservation(
        dtReservation.getTime(),
        dtReservation.getDate(),
        dtSchedule.getFraction(),
        dtSchedule.getId(),
        dtSchedule.getVaccinationCenter().getVaccinationPosts().size()
      )
    ) {
      System.out.println("hay lugar");
      iReservationData.saveReservation(dtReservation);
      return 0;
    } else {
      System.out.println("no hay lugar");
      return 1;
    }
  }

  @Override
  public int createReservation2(DTReservation dtReservation) {
    dtReservation.setDoses(1);
    DTSchedule dtSchedule = new DTSchedule();
    DTVaccinationCenter dtVaccinationCenter = new DTVaccinationCenter() ;
    DTCitizen dtCitizen = new DTCitizen();
    DTVaccinationPost dtVaccinationPost = null;
    int postCount = 0;
    if (dtReservation.getVaccinationCenter() != null) {
      dtVaccinationCenter = iVaccinationCenterData.getVaccinationCenterById(Long.parseLong(dtReservation.getVaccinationCenter()));
      postCount = dtVaccinationCenter.getVaccinationPosts().size();
    }
    if (dtReservation.getCitizen() != null) {
      dtCitizen = iCitizenController.getCitizenByCi(dtReservation.getCitizen());
    }
    if (dtReservation.getSchedule() != null) {
      dtSchedule = iScheduleData.getScheduleById(Long.parseLong(dtReservation.getSchedule()));

      for (int i = 0; i < dtSchedule.getVaccinationPlan().getVaccine().getDoseQuantity(); i++) {
        if (
          iReservationData.availableReservation(
            dtReservation.getTime(),
            dtReservation.getDate(),
            dtSchedule.getFraction(),
            dtSchedule.getId(),
            postCount
          )
        ) {
          dtVaccinationPost = iReservationData.assignReservationToBestPost(dtVaccinationCenter.getId());
          iReservationData.createReservation(dtReservation, dtSchedule, dtCitizen, dtVaccinationCenter, dtVaccinationPost);
          dtReservation =
            getAvailableDateDose(
              dtReservation,
              dtSchedule.isSaturday(),
              dtSchedule.isSunday(),
              dtSchedule.getVaccinationPlan().getVaccine().getMonthQuantity()
            );
        } else {
          return 0;
        }
      }
      return 1;
    }
    return 0;
  }

  @Override
  public List<DTReservationView> getReservationData(String ci) {
    return iReservationData.getReservationData(ci);
  }

  @Override
  public boolean reservationChangeState(Long idReservation, ReservationState reservationState) {
    return iReservationData.reservationChangeState(idReservation, reservationState);
  }

  @Override
  public void reservationToConfirmed(Long reservationId) {
    iReservationData.reservationToConfirmed(reservationId);
  }

  @Override
  public List<DTPackage> dtPackageFromReservation(Long id) {
    return iReservationData.dtPackageFromReservation(id);
  }

  @Override
  public DTVaccinationPost dtVaccinationPostFromReservation(Long id) {
    return iReservationData.dtVaccinationPostFromReservation(id);
  }

  @Override
  public DTVaccinationPost assignReservationToBestPost(Long centerId) {
    return iReservationData.assignReservationToBestPost(centerId);
  }

  @Override
  public DTCitizen dtCitizenFromReservation(Long id) {
    return iReservationData.dtCitizenFromReservation(id);
  }

  @Override
  public List<DTCitizenToken> getCitizenTokenByReservation() {
    return iReservationData.getCitizenTokenByReservation();
  }

  public void setiReservationData(IReservationData iReservationData) {
    this.iReservationData = iReservationData;
  }

  public void setiScheduleData(IScheduleData iScheduleData) {
    this.iScheduleData = iScheduleData;
  }

  public void setiVaccinationCenterData(IVaccinationCenterData iVaccinationCenterData) {
    this.iVaccinationCenterData = iVaccinationCenterData;
  }

  public void setiCitizenController(ICitizenController iCitizenController) {
    this.iCitizenController = iCitizenController;
  }

  @Override
  public DtMonitorDate getCountWeek(LocalDate startDate, LocalDate endDate, Long idVPlan) {
    return iReservationData.getCountWeek(startDate, endDate, idVPlan);
  }

  public DTReservation getAvailableDateDose(DTReservation dtReservation, Boolean sat, Boolean sun, Integer monthQuantity) {
    LocalDate reservationDate = LocalDate.parse(dtReservation.getDate());
    dtReservation.setDoses(dtReservation.getDoses() + 1);
    while (monthQuantity > 7) {
      reservationDate = reservationDate.plusWeeks(1);
      monthQuantity = monthQuantity - 7;
      dtReservation.setDate(reservationDate.toString());
    }
    while (reservationDate.getDayOfWeek() == DayOfWeek.SUNDAY || reservationDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
      if ((reservationDate.getDayOfWeek() == DayOfWeek.SUNDAY) && sun) {
        return dtReservation;
      }
      if ((reservationDate.getDayOfWeek() == DayOfWeek.SATURDAY) && sat) {
        return dtReservation;
      }
      reservationDate = reservationDate.plusDays(1);
    }
    dtReservation.setDate(reservationDate.toString());
    return dtReservation;
  }
}
