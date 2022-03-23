package Controllers;

import static org.mockito.Mockito.*;

import Controller.ReservationController;
import DTO.*;
import IController.ICitizenController;
import IDAL.IReservationData;
import IDAL.IScheduleData;
import IDAL.IVaccinationCenterData;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReservationControllerTests {

  @Spy
  private IReservationData iReservationData;

  @Spy
  private IScheduleData iScheduleData;

  @Spy
  private IVaccinationCenterData iVaccinationCenterData;

  @Spy
  private ICitizenController iCitizenController;

  private ReservationController reservationController;

  @Before
  public void setup() {
    this.iReservationData = mock(IReservationData.class);
    this.iScheduleData = mock(IScheduleData.class);
    this.iVaccinationCenterData = mock(IVaccinationCenterData.class);
    this.iCitizenController = mock(ICitizenController.class);
    this.reservationController = new ReservationController();
    this.reservationController.setiReservationData(iReservationData);
    this.reservationController.setiScheduleData(iScheduleData);
    this.reservationController.setiVaccinationCenterData(iVaccinationCenterData);
    this.reservationController.setiCitizenController(iCitizenController);
  }

  @After
  public void teardown() {
    Mockito.reset(iReservationData);
    Mockito.reset(iScheduleData);
    Mockito.reset(iVaccinationCenterData);
    Mockito.reset(iCitizenController);
  }

  @Test
  public void saveReservationTest() {
    DTReservation dtr = new DTReservation();
    reservationController.saveReservation(dtr);
    Mockito.verify(iReservationData, times(1)).saveReservation(dtr);
  }

  @Test
  public void getReservationsByScheduleTest() {
    reservationController.getReservationsBySchedule(anyLong());
    Mockito.verify(iReservationData, times(1)).getReservationsBySchedule(anyLong());
  }

  @Test
  public void getByIdReservationTest() {
    reservationController.getByIdReservation(anyLong());
    Mockito.verify(iReservationData, times(1)).getByIdReservation(anyLong());
  }

  @Test
  public void getReservationsTest() {
    reservationController.getReservations();
    Mockito.verify(iReservationData, times(1)).getReservations();
  }

  @Test
  public void deleteReservationTest() {
    reservationController.deleteReservation(anyLong());
    Mockito.verify(iReservationData, times(1)).deleteReservation(anyLong());
  }

  @Test
  public void unavailableDateTest() {
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    when(iScheduleData.getScheduleById(anyLong())).thenReturn(dts);
    reservationController.unavailableDate(anyLong());

    Mockito.verify(iReservationData, times(1)).unavailableDate(dts);
  }

  @Test
  public void unavailableTimeTest() {
    String a = "Test";
    long b = 1L;
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    when(iScheduleData.getScheduleById(1L)).thenReturn(dts);
    reservationController.unavailableTime(a, b);

    Mockito.verify(iReservationData, times(1)).unavailableTime(a, b, dts.getFraction() * 2);
  }

  @Test
  public void getReservationsSendTest() {
    reservationController.getReservationsSend(anyLong());
    Mockito.verify(iReservationData, times(1)).getReservationsSend(anyLong());
  }

  @Test
  public void getReservationsSend2Test() {
    reservationController.getReservationsSend2(anyLong());
    Mockito.verify(iReservationData, times(1)).getReservationsSend2(anyLong());
  }

  @Test
  public void getReservationDataTest() {
    reservationController.getReservationData(anyString());
    Mockito.verify(iReservationData, times(1)).getReservationData(anyString());
  }

  @Test
  public void reservationChangeStateTest() {
    reservationController.reservationChangeState(anyLong(), any());
    Mockito.verify(iReservationData, times(1)).reservationChangeState(anyLong(), any());
  }

  @Test
  public void reservationToConfirmedTest() {
    reservationController.reservationToConfirmed(anyLong());
    Mockito.verify(iReservationData, times(1)).reservationToConfirmed(anyLong());
  }

  @Test
  public void dtPackageFromReservationTest() {
    reservationController.dtPackageFromReservation(anyLong());
    Mockito.verify(iReservationData, times(1)).dtPackageFromReservation(anyLong());
  }

  @Test
  public void dtVaccinationPostFromReservation() {
    reservationController.dtVaccinationPostFromReservation(anyLong());
    Mockito.verify(iReservationData, times(1)).dtVaccinationPostFromReservation(anyLong());
  }

  @Test
  public void dtCitizenFromReservation() {
    reservationController.dtCitizenFromReservation(anyLong());
    Mockito.verify(iReservationData, times(1)).dtCitizenFromReservation(anyLong());
  }

  @Test
  public void createReservationTestWhenAvailableSpots() {
    DTReservation dtr = new DTReservation();
    dtr.setSchedule("1");
    dtr.setTime("00:00:00");
    dtr.setDate("2021-01-01");
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> lS = new ArrayList<>();
    lS.add("Test");
    dtvc.setVaccinationPosts(lS);
    dts.setVaccinationCenter(dtvc);
    when(iScheduleData.getScheduleById(anyLong())).thenReturn(dts);
    when(iReservationData.availableReservation(dtr.getTime(), dtr.getDate(), dts.getFraction(), dts.getId(), 1)).thenReturn(true);

    Assert.assertEquals(0, reservationController.createReservation(dtr));
    Mockito.verify(iReservationData, times(1)).saveReservation(any());
  }

  @Test
  public void createReservationTestWhenNoAvailableSpots() {
    DTReservation dtr = new DTReservation();
    dtr.setSchedule("1");
    dtr.setTime("00:00:00");
    dtr.setDate("2021-01-01");
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> lS = new ArrayList<>();
    lS.add("Test");
    dtvc.setVaccinationPosts(lS);
    dts.setVaccinationCenter(dtvc);
    when(iScheduleData.getScheduleById(anyLong())).thenReturn(dts);
    when(iReservationData.availableReservation(dtr.getTime(), dtr.getDate(), dts.getFraction(), dts.getId(), 0)).thenReturn(false);

    Assert.assertEquals(1, reservationController.createReservation(dtr));
  }

  @Test
  public void createReservation2TestWhenAvailableSpots() { //ajustar
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> lVp = new ArrayList<>();
    lVp.add("Test");
    dtvc.setVaccinationPosts(lVp);
    DTCitizen dtc = new DTCitizen();
    DTReservation dtr = new DTReservation();
    DTVaccinationPost dtvp = new DTVaccinationPost();
    dtr.setSchedule("1");
    dtr.setTime("00:00:00");
    dtr.setDate("2021-01-01");
    dtr.setVaccinationCenter("1");
    dtr.setCitizen("1");
    dtr.setSchedule("1");
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    DTVaccinationPlan dtvpl = new DTVaccinationPlan();
    DTVaccine dtv = new DTVaccine();
    dtv.setDoseQuantity(1);
    dtvpl.setVaccine(dtv);
    dts.setVaccinationPlan(dtvpl);
    when(iVaccinationCenterData.getVaccinationCenterById(Long.parseLong(dtr.getVaccinationCenter()))).thenReturn(dtvc);
    when(iCitizenController.getCitizenByCi(dtr.getCitizen())).thenReturn(dtc);
    when(iScheduleData.getScheduleById(anyLong())).thenReturn(dts);
    when(iReservationData.availableReservation(dtr.getTime(), dtr.getDate(), dts.getFraction(), dts.getId(), 1)).thenReturn(true);
    when(iReservationData.assignReservationToBestPost(dtvc.getId())).thenReturn(new DTVaccinationPost());

    Assert.assertEquals(1, reservationController.createReservation2(dtr));
  }

  @Test
  public void createReservation2TestWhenNoAvailableSpots() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> lVp = new ArrayList<>();
    lVp.add("Test");
    dtvc.setVaccinationPosts(lVp);
    DTCitizen dtc = new DTCitizen();
    DTReservation dtr = new DTReservation();
    DTVaccinationPost dtvp = new DTVaccinationPost();
    dtr.setSchedule("1");
    dtr.setTime("00:00:00");
    dtr.setDate("2021-01-01");
    dtr.setVaccinationCenter("1");
    dtr.setCitizen("1");
    dtr.setSchedule("1");
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);
    DTVaccinationPlan dtvpl = new DTVaccinationPlan();
    DTVaccine dtv = new DTVaccine();
    dtv.setDoseQuantity(1);
    dtvpl.setVaccine(dtv);
    dts.setVaccinationPlan(dtvpl);
    when(iVaccinationCenterData.getVaccinationCenterById(Long.parseLong(dtr.getVaccinationCenter()))).thenReturn(dtvc);
    when(iCitizenController.getCitizenByCi(dtr.getCitizen())).thenReturn(dtc);
    when(iScheduleData.getScheduleById(anyLong())).thenReturn(dts);

    Assert.assertEquals(0, reservationController.createReservation2(dtr));
  }

  @Test
  public void createReservation2TestWhenScheduleNull() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    DTCitizen dtc = new DTCitizen();
    DTReservation dtr = new DTReservation();
    DTVaccinationPost dtvp = new DTVaccinationPost();
    dtr.setSchedule("1");
    dtr.setTime("00:00:00");
    dtr.setDate("2021-01-01");
    dtr.setSchedule(null);
    DTSchedule dts = new DTSchedule("2021-01-01", "2021-01-02", "00:00:00", "23:59:59", 15, true, false);

    Assert.assertEquals(0, reservationController.createReservation2(dtr));
    Mockito.verify(iReservationData, times(0)).createReservation(dtr, dts, dtc, dtvc, dtvp);
  }

  @Test
  public void getCitizenTokenByReservationTest() {
    reservationController.getCitizenTokenByReservation();
    Mockito.verify(iReservationData, times(1)).getCitizenTokenByReservation();
  }

  @Test
  public void assignReservationToBestPostTest() {
    reservationController.assignReservationToBestPost(1L);
    Mockito.verify(iReservationData, times(1)).assignReservationToBestPost(1L);
  }

  @Test
  public void getCountWeekTest() {
    reservationController.getCountWeek(any(), any(), anyLong());
    Mockito.verify(iReservationData, times(1)).getCountWeek(any(), any(), anyLong());
  }

  @Test
  public void getAvailableDateDoseTestNoWeekend() {
    DTReservation dtr = new DTReservation();
    dtr.setDate("2021-01-01");
    dtr.setDoses(1);

    reservationController.getAvailableDateDose(dtr, true, true, 8);
  }

  @Test
  public void getAvailableDateDoseTestSaturday() {
    DTReservation dtr = new DTReservation();
    dtr.setDate("2021-01-02");
    dtr.setDoses(1);

    reservationController.getAvailableDateDose(dtr, true, true, 8);
  }

  @Test
  public void getAvailableDateDoseTestSunday() {
    DTReservation dtr = new DTReservation();
    dtr.setDate("2021-01-03");
    dtr.setDoses(1);

    reservationController.getAvailableDateDose(dtr, true, true, 8);
  }

  @Test
  public void getAvailableDateDoseTestInWeek() {
    DTReservation dtr = new DTReservation();
    dtr.setDate("2021-01-03");
    dtr.setDoses(1);

    reservationController.getAvailableDateDose(dtr, false, false, 8);
  }
}
