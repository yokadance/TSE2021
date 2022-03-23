package Controller;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;
import DTO.DtMonitorDate;
import IController.IReservationController;
import IController.IVaccinationPlanController;
import IDAL.IVaccinationCenterData;
import IDAL.IVaccinationPlanData;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class VaccinationPlanController implements IVaccinationPlanController {

  @EJB
  IVaccinationPlanData iVaccinationPlanData;

  @EJB
  IVaccinationCenterData iVaccinationCenterData;

  @Inject
  IReservationController iReservationController;

  @Override
  public void newVaccinationPlan(DTVaccinationPlan dtVaccinationPlan) {}

  @Override
  public void unassignCenterOfPlan(Long centerId, Long planId) {
    iVaccinationPlanData.unassignCenterOfPlan(centerId, planId);
  }

  @Override
  public List<DTVaccinationPlan> listVaccinationPlans() {
    return iVaccinationPlanData.listVaccinationsPlans();
  }

  @Override
  public Long saveVaccinationPlan(DTVaccinationPlan dtVaccinationPlan) {
    List<DTVaccinationCenter> vaccinationCenterList = new ArrayList<>();
    if (dtVaccinationPlan.getVaccinationCenters() != null) {
      dtVaccinationPlan
        .getVaccinationCenters()
        .forEach(
          vaccinationCenter -> {
            DTVaccinationCenter dtVaccinationCenter = iVaccinationCenterData.getVaccinationCenterById(Long.parseLong(vaccinationCenter));
            vaccinationCenterList.add(dtVaccinationCenter);
          }
        );
    }
    return iVaccinationPlanData.saveVaccinationPlan(dtVaccinationPlan, vaccinationCenterList);
  }

  @Override
  public void deleteVaccinationPlan(Long id) {
    iVaccinationPlanData.deleteVaccinationPlan(id);
  }

  @Override
  public DTVaccinationPlan getVaccinationPlanById(Long id) {
    return iVaccinationPlanData.getVaccinationPlanById(id);
  }

  @Override
  public List<DTVaccinationCenter> vaccinationCentersByVaccinationPlan(Long id) {
    return iVaccinationPlanData.vaccinationCentersByVaccinationPlan(id);
  }

  @Override
  public void addCenterToPlan(Long planId, Long centerId) {
    iVaccinationPlanData.addCenterToPlan(planId, centerId);
  }

  @Override
  public void addRestrictionToPlan(Long planId, Long restrictionId) {
    iVaccinationPlanData.addRestrictionToPlan(planId, restrictionId);
  }

  public void setiVaccinationPlanData(IVaccinationPlanData iVaccinationPlanData) {
    this.iVaccinationPlanData = iVaccinationPlanData;
  }

  public void setiVaccinationCenterData(IVaccinationCenterData iVaccinationCenterData) {
    this.iVaccinationCenterData = iVaccinationCenterData;
  }

  @Override
  public DTVaccinationPlanMonitor getDataMonVPlan(Long id) {
    return getDosesByDay(iVaccinationPlanData.getDataMonVPlan(id));
  }

  public DTVaccinationPlanMonitor getDosesByDay(DTVaccinationPlanMonitor dtVaccinationPlanMonitor) {
    List<DtMonitorDate> dateList = new ArrayList<>();
    dtVaccinationPlanMonitor.getStartDate();
    dtVaccinationPlanMonitor.getEndDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate endDate = LocalDate.parse(dtVaccinationPlanMonitor.getEndDate(), formatter);
    LocalDate startDate = LocalDate.parse(dtVaccinationPlanMonitor.getStartDate(), formatter);
    Long countotal1 = 0L;
    Long countotal2 = 0L;
    Long countotal3 = 0L;
    Long countotal4 = 0L;
    Long countotal5 = 0L;

    while (!endDate.isBefore(startDate)) {
      DtMonitorDate dtMonitorDate = new DtMonitorDate();
      System.out.println(startDate);
      System.out.println(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
      System.out.println(startDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));
      System.out.println(LocalDate.now());
      if (
        startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).isEqual(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)))
      ) {
        dtMonitorDate =
          iReservationController.getCountWeek(
            LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY)),
            LocalDate.now(),
            dtVaccinationPlanMonitor.getvPlanId()
          );
        if (dtMonitorDate.getDoses1() != null) {
          countotal1 = countotal1 + dtMonitorDate.getDoses1();
        }
        if (dtMonitorDate.getDoses2() != null) {
          countotal2 = countotal2 + dtMonitorDate.getDoses2();
        }
        if (dtMonitorDate.getDoses3() != null) {
          countotal3 = countotal3 + dtMonitorDate.getDoses3();
        }
        if (dtMonitorDate.getDoses4() != null) {
          countotal4 = countotal4 + dtMonitorDate.getDoses4();
        }
        if (dtMonitorDate.getDoses5() != null) {
          countotal5 = countotal5 + dtMonitorDate.getDoses5();
        }

        dtMonitorDate.setDoses1(countotal1);
        dtMonitorDate.setDoses2(countotal2);
        dtMonitorDate.setDoses3(countotal3);
        dtMonitorDate.setDoses4(countotal4);
        dtMonitorDate.setDoses5(countotal5);
        dateList.add(dtMonitorDate);
        break;
      } else {
        dtMonitorDate =
          iReservationController.getCountWeek(
            startDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)),
            startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)),
            dtVaccinationPlanMonitor.getvPlanId()
          );
        startDate = startDate.plusWeeks(1);

        if (dtMonitorDate.getDoses1() != null) {
          countotal1 = countotal1 + dtMonitorDate.getDoses1();
        }
        if (dtMonitorDate.getDoses2() != null) {
          countotal2 = countotal2 + dtMonitorDate.getDoses2();
        }
        if (dtMonitorDate.getDoses3() != null) {
          countotal3 = countotal3 + dtMonitorDate.getDoses3();
        }
        if (dtMonitorDate.getDoses4() != null) {
          countotal4 = countotal4 + dtMonitorDate.getDoses4();
        }
        if (dtMonitorDate.getDoses5() != null) {
          countotal5 = countotal5 + dtMonitorDate.getDoses5();
        }

        dtMonitorDate.setDoses1(countotal1);
        dtMonitorDate.setDoses2(countotal2);
        dtMonitorDate.setDoses3(countotal3);
        dtMonitorDate.setDoses4(countotal4);
        dtMonitorDate.setDoses5(countotal5);
        dateList.add(dtMonitorDate);
      }
    }
    dtVaccinationPlanMonitor.setDoses1(countotal1);
    dtVaccinationPlanMonitor.setDoses2(countotal2);
    dtVaccinationPlanMonitor.setDoses3(countotal3);
    dtVaccinationPlanMonitor.setDoses4(countotal4);
    dtVaccinationPlanMonitor.setDoses5(countotal5);
    dtVaccinationPlanMonitor.setToday(LocalDate.now().toString());
    dtVaccinationPlanMonitor.setDates(dateList);
    return dtVaccinationPlanMonitor;
  }
}
