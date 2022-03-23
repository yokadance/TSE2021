package Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import Controller.VaccinationPlanController;
import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import DTO.DTVaccinationPlanMonitor;
import DTO.DtMonitorDate;
import IController.IReservationController;
import IDAL.IVaccinationCenterData;
import IDAL.IVaccinationPlanData;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationPlanControllerTests {

  @Spy
  private IVaccinationPlanData iVaccinationPlanData;

  @Spy
  private IVaccinationCenterData iVaccinationCenterData;

  @Spy
  private IReservationController iReservationController;

  private VaccinationPlanController vaccinationPlanController;

  @Before
  public void setup() {
    this.iVaccinationPlanData = mock(IVaccinationPlanData.class);
    this.iVaccinationCenterData = mock(IVaccinationCenterData.class);
    this.iReservationController = mock(IReservationController.class);
    this.vaccinationPlanController = new VaccinationPlanController();
    this.vaccinationPlanController.setiVaccinationPlanData(iVaccinationPlanData);
    this.vaccinationPlanController.setiVaccinationCenterData(iVaccinationCenterData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationCenterData);
    Mockito.reset(iVaccinationPlanData);
    Mockito.reset(iReservationController);
  }

  @Test
  public void newVaccinationPlanTest() {
    DTVaccinationPlan dtvp = new DTVaccinationPlan();
    vaccinationPlanController.newVaccinationPlan(dtvp);
  }

  @Test
  public void listVaccinationPlansTest() {
    vaccinationPlanController.listVaccinationPlans();
  }

  @Test
  public void saveVaccinationPlanTest() {
    DTVaccinationPlan dtvp = new DTVaccinationPlan();
    List<String> lStr = new ArrayList<>();
    lStr.add("1");
    dtvp.setVaccinationCenters(lStr);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(iVaccinationCenterData.getVaccinationCenterById(anyLong())).thenReturn(dtvc);

    vaccinationPlanController.saveVaccinationPlan(dtvp);
    Mockito.verify(iVaccinationPlanData, times(1)).saveVaccinationPlan(any(), any());
  }

  @Test
  public void deleteVaccinationPlanTest() {
    vaccinationPlanController.deleteVaccinationPlan(anyLong());
    Mockito.verify(iVaccinationPlanData, times(1)).deleteVaccinationPlan(anyLong());
  }

  @Test
  public void getVaccinationPlanByIdTest() {
    vaccinationPlanController.getVaccinationPlanById(anyLong());
    Mockito.verify(iVaccinationPlanData, times(1)).getVaccinationPlanById(anyLong());
  }

  @Test
  public void vaccinationCentersByVaccinationPlanTest() {
    vaccinationPlanController.vaccinationCentersByVaccinationPlan(anyLong());
    Mockito.verify(iVaccinationPlanData, times(1)).vaccinationCentersByVaccinationPlan(anyLong());
  }

  @Test
  public void addCenterToPlanTest() {
    vaccinationPlanController.addCenterToPlan(anyLong(), anyLong());
    Mockito.verify(iVaccinationPlanData, times(1)).addCenterToPlan(anyLong(), anyLong());
  }

  @Test
  public void addRestrictionToPlanTest() {
    vaccinationPlanController.addRestrictionToPlan(anyLong(), anyLong());
    Mockito.verify(iVaccinationPlanData, times(1)).addRestrictionToPlan(anyLong(), anyLong());
  }

  @Test
  public void getDosesByDayTest() {
    DTVaccinationPlanMonitor dtvpm = new DTVaccinationPlanMonitor();
    dtvpm.setvPlanId(1L);
    dtvpm.setStartDate("2021-01-01");
    dtvpm.setEndDate("2020-01-11");

    vaccinationPlanController.getDosesByDay(dtvpm);
  }

  @Test
  public void getDataMonVPlanTest() {
    DTVaccinationPlanMonitor dtvpm = new DTVaccinationPlanMonitor();
    dtvpm.setvPlanId(1L);
    dtvpm.setStartDate("2021-01-01");
    dtvpm.setEndDate("2020-01-11");
    when(iVaccinationPlanData.getDataMonVPlan(1L)).thenReturn(dtvpm);

    vaccinationPlanController.getDataMonVPlan(1L);
    Mockito.verify(iVaccinationPlanData, times(1)).getDataMonVPlan(1L);
  }

  @Test
  public void unassignCenterOfPlanTest() {
    vaccinationPlanController.unassignCenterOfPlan(1L, 1L);
    Mockito.verify(iVaccinationPlanData, times(1)).unassignCenterOfPlan(1L, 1L);
  }
}
