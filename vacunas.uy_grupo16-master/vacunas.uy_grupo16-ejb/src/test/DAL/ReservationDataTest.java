package DAL;

import DTO.*;
import entities.Package;
import entities.*;
import enumerations.ReservationState;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationDataTest {

  private ReservationData reservationData;

  private Reservation reservation;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPost vaccinationPost;

  private Citizen citizen;

  private Person person;

  private VaccinationPlan vaccinationPlan;

  private VaccinationCenter vaccinationCenter;

  private Package aPackage;

  private Batch batch;

  private Vaccine vaccine;

  private Schedule schedule;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    batch = mock(Batch.class);
    aPackage = mock(Package.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    person = mock(Person.class);
    citizen = mock(Citizen.class);
    vaccinationPost = mock(VaccinationPost.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    reservation = mock(Reservation.class);
    schedule = mock(Schedule.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.reservationData = new ReservationData();
    this.reservationData.setData(data);
    this.reservationData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(reservation);
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(schedule);
    Mockito.reset(vaccine);
    Mockito.reset(batch);
    Mockito.reset(aPackage);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(person);
    Mockito.reset(citizen);
    Mockito.reset(vaccinationPost);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveReservationTestWithIdNotNull() {
    Date date = new Date();
    DTReservation dtr = new DTReservation();
    dtr.setId(1L);
    reservation.setId(1L);
    reservation.setCreateDate(date);
    when(modelMapper.map(dtr, Reservation.class)).thenReturn(reservation);
    when(data.find(any(), any())).thenReturn(reservation);

    reservationData.saveReservation(dtr);
  }

  @Test
  public void saveReservationTestWithIdNull() {
    Date date = new Date();
    DTReservation dtr = new DTReservation();
    dtr.setId(null);
    reservation.setId(null);
    reservation.setCreateDate(date);
    when(modelMapper.map(dtr, Reservation.class)).thenReturn(reservation);
    when(reservation.getId()).thenReturn(null);

    reservationData.saveReservation(dtr);
  }

  @Test
  public void createReservationTestWithCatch() {
    DTReservation dtr = new DTReservation();
    DTCitizen dtc = new DTCitizen();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    DTSchedule dts = new DTSchedule();
    Citizen citizen = new Citizen();
    DTVaccinationPost dtvp = new DTVaccinationPost();
    VaccinationCenter vaccinationCenter = new VaccinationCenter();
    dtr.setCitizen("1");
    dtr.setVaccinationCenter("1");
    dtr.setSchedule("1");

    when(modelMapper.map(dtc, Citizen.class)).thenReturn(citizen);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(modelMapper.map(dts, Schedule.class)).thenReturn(schedule);
    when(modelMapper.map(dtr, Reservation.class)).thenReturn(reservation);

    reservationData.createReservation(dtr, dts, dtc, dtvc, dtvp);
    Assert.assertEquals(0, reservationData.createReservation(dtr, dts, dtc, dtvc, dtvp));
  }

  @Test
  public void createReservationTestWithNoError() {
    DTReservation dtr = new DTReservation();
    DTCitizen dtc = new DTCitizen();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    DTSchedule dts = new DTSchedule();
    Citizen citizen = new Citizen();
    DTVaccinationPost dtvp = new DTVaccinationPost();
    VaccinationCenter vaccinationCenter = new VaccinationCenter();
    dtr.setCitizen("1");
    dtr.setVaccinationCenter("1");
    dtr.setSchedule("1");

    when(modelMapper.map(dtc, Citizen.class)).thenReturn(citizen);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(modelMapper.map(dts, Schedule.class)).thenReturn(schedule);
    when(modelMapper.map(dtr, Reservation.class)).thenReturn(reservation);
    when(reservation.getId()).thenReturn(1L);
    when(data.merge(reservation)).thenReturn(reservation);

    reservationData.createReservation(dtr, dts, dtc, dtvc, dtvp);
    Assert.assertEquals(1L, reservationData.createReservation(dtr, dts, dtc, dtvc, dtvp));
  }

  @Test
  public void getReservationsTest() {
    List<Reservation> lRes = new ArrayList<>();
    lRes.add(reservation);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lRes);

    reservationData.getReservations();
  }

  @Test
  public void getByIdReservationTest() {
    DTReservation dtr = new DTReservation();
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);
    when(reservation.getDTReservation()).thenReturn(dtr);

    reservationData.getByIdReservation(1L);
    Assert.assertEquals(dtr, reservationData.getByIdReservation(1L));
  }

  @Test
  public void deleteReservationTest() {
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);

    reservationData.deleteReservation(1L);
  }

  @Test
  public void getReservationsByScheduleTest() {
    List<Reservation> lRes = new ArrayList<>();
    lRes.add(reservation);
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lRes);

    reservationData.getReservationsBySchedule(1L);
  }

  @Test
  public void availableReservationTestReturnTrue() {
    String reservationDate = "2021-01-01";
    String reservationTime = "09:00";
    List<Reservation> lRes = new ArrayList<>();
    lRes.add(reservation);
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationDate", reservationDate)).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationTime", reservationTime)).thenReturn(typedQuery);
    when(typedQuery.setParameter("schId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lRes);

    reservationData.availableReservation(reservationTime, reservationDate, 1, 1L, 1);
    Assert.assertEquals(true, reservationData.availableReservation(reservationTime, reservationDate, 1, 1L, 1));
  }

  @Test
  public void availableReservationTestReturnFalse() {
    String reservationDate = "2021-01-01";
    String reservationTime = "09:00";
    List<Reservation> lRes = new ArrayList<>();
    lRes.add(reservation);
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationDate", reservationDate)).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationTime", reservationTime)).thenReturn(typedQuery);
    when(typedQuery.setParameter("schId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lRes);

    reservationData.availableReservation(reservationTime, reservationDate, 1, 1L, 1);
    Assert.assertEquals(false, reservationData.availableReservation(reservationTime, reservationDate, 0, 1L, 1));
  }

  @Test
  public void unavailableDateTest() {
    DTSchedule dts = new DTSchedule();
    dts.setId(1L);
    dts.setEndDate("2022-01-01");
    dts.setStartDate("2021-01-01");
    dts.setStartTimeDaily("09:00");
    dts.setEndTimeDaily("09:00");
    List<Object[]> listObject = new ArrayList<>();
    Object[] object = new Object[] { 11L, "Test" };
    listObject.add(object);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationEndDate", dts.getEndDate())).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationStartDate", dts.getStartDate())).thenReturn(typedQuery);
    when(typedQuery.setParameter("idshedule", dts.getId())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(listObject);

    reservationData.unavailableDate(dts);
  }

  @Test
  public void unavailableTimeTest() {
    long ids = 1L;
    String reservationDate = "2022-01-01";
    int quotas = 1;
    List<Object[]> listObject = new ArrayList<>();
    Object[] object = new Object[] { 11L, "Test" };
    listObject.add(object);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("reservationDate", reservationDate)).thenReturn(typedQuery);
    when(typedQuery.setParameter("idShcedule", ids)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(listObject);

    reservationData.unavailableTime(reservationDate, ids, quotas);
  }

  @Test
  public void getReservationsSendTest() {
    long ids = 1L;
    List<Reservation> lsr = new ArrayList<>();
    lsr.add(reservation);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("idShcedule", ids)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lsr);
    when(reservation.getSchedule()).thenReturn(schedule);
    when(schedule.getId()).thenReturn(1L);
    when(reservation.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(1L);
    when(reservation.getCitizen()).thenReturn(citizen);
    when(citizen.getId()).thenReturn(1L);
    when(citizen.getPerson()).thenReturn(person);
    when(person.getId()).thenReturn(1L);

    reservationData.getReservationsSend(ids);
  }

  @Test
  public void getReservationDataTest() {
    String ci = "test";
    List<Reservation> lsr = new ArrayList<>();
    lsr.add(reservation);
    List<Package> lp = new ArrayList<>();
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lsr);
    when(reservation.getId()).thenReturn(1L);
    when(reservation.getSchedule()).thenReturn(schedule);
    when(schedule.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(reservation.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(reservation.getDate()).thenReturn("Test");
    when(reservation.getTime()).thenReturn("Test");
    when(reservation.getReservationState()).thenReturn(ReservationState.rejected);

    reservationData.getReservationData(ci);
  }

  @Test
  public void reservationChangeStateTestWhenReturnTrue() {
    when(data.find(any(), any())).thenReturn(reservation);

    reservationData.reservationChangeState(1L, ReservationState.pending);
    Assert.assertEquals(true, reservationData.reservationChangeState(1L, ReservationState.pending));
  }

  @Test
  public void reservationChangeStateTestWhenReturnFalse() {
    Long idReservation = null;
    ReservationState reservationState = null;

    reservationData.reservationChangeState(idReservation, ReservationState.pending);
    Assert.assertEquals(false, reservationData.reservationChangeState(idReservation, reservationState));
  }

  @Test
  public void getReservationsSend2Test() {
    Long idShcedule = 1L;
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    List<Reservation> lsr = new ArrayList<>();
    lsr.add(reservation);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("idShcedule", idShcedule)).thenReturn(typedQuery);
    when(typedQuery.setParameter("today", today)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lsr);
    when(reservation.getSchedule()).thenReturn(schedule);
    when(schedule.getId()).thenReturn(1L);
    when(reservation.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getId()).thenReturn(1L);
    when(reservation.getCitizen()).thenReturn(citizen);
    when(citizen.getPerson()).thenReturn(person);
    when(person.getName()).thenReturn("Test");
    when(person.getLastname()).thenReturn("Test");
    when(person.getCi()).thenReturn("Test");
    when(reservation.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(1L);

    reservationData.getReservationsSend2(idShcedule);
  }

  @Test
  public void reservationToConfirmedTest() {
    reservation.setId(1L);
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);
    when(data.merge(reservation)).thenReturn(reservation);

    reservationData.reservationToConfirmed(1L);
  }

  @Test
  public void dtCitizenFromReservationTest() {
    reservation.setId(1L);
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);
    when(reservation.getCitizen()).thenReturn(citizen);
    when(citizen.getId()).thenReturn(1L);

    reservationData.dtCitizenFromReservation(1L);
  }

  @Test
  public void dtVaccinationPostFromReservationTest() {
    reservation.setId(1L);
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);
    when(reservation.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(1L);

    reservationData.dtVaccinationPostFromReservation(1L);
  }

  @Test
  public void dtPackageFromReservationTest() {
    List<Package> lPackage = new ArrayList<>();
    lPackage.add(aPackage);
    reservation.setId(1L);
    when(data.find(Reservation.class, 1L)).thenReturn(reservation);
    when(reservation.getSchedule()).thenReturn(schedule);
    when(schedule.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getaPackage()).thenReturn(lPackage);
    when(vaccinationPost.getId()).thenReturn(1L);

    reservationData.dtPackageFromReservation(1L);
  }

  @Test
  public void getCitizenTokenByReservationTestReturnOk() {
    List<Reservation> lsr = new ArrayList<>();
    lsr.add(reservation);
    String datenow = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusDays(1L));
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("datenow", datenow)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lsr);
    when(reservation.getCitizen()).thenReturn(citizen);
    when(citizen.getPerson()).thenReturn(person);
    when(person.getName()).thenReturn("Test");
    when(citizen.getToken()).thenReturn("Test");
    when(reservation.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(reservation.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getName()).thenReturn("Test");
    when(reservation.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getLocation()).thenReturn("Test");

    reservationData.getCitizenTokenByReservation();
  }

  @Test
  public void getCitizenTokenByReservationTestReturnNull() {
    List<Reservation> lsr = new ArrayList<>();
    lsr.add(reservation);
    String datenow = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusDays(1L));
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("datenow", datenow)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(null);

    reservationData.getCitizenTokenByReservation();
  }
}
