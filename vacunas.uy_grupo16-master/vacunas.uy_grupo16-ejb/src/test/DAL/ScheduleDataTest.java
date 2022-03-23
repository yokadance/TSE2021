package DAL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTSchedule;
import DTO.DTVaccinationPlan;
import entities.Reservation;
import entities.Schedule;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleDataTest {

  private ScheduleData scheduleData;

  private Schedule schedule;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPlan vaccinationPlan;

  private VaccinationCenter vaccinationCenter;

  private Reservation reservation;

  @Before
  public void setup() {
    vaccinationPlan = mock(VaccinationPlan.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    schedule = mock(Schedule.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    reservation = mock(Reservation.class);
    this.scheduleData = new ScheduleData();
    this.scheduleData.setData(data);
    this.scheduleData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(schedule);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(reservation);
  }

  @Test
  public void saveScheduleTestWithId() {
    DTSchedule dts = new DTSchedule();
    schedule.setId(1L);
    when(modelMapper.map(dts, Schedule.class)).thenReturn(schedule);
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);
    when(schedule.getId()).thenReturn(1L);
    when(schedule.getCreateDate()).thenReturn(new Date());

    scheduleData.saveSchedule(dts);
  }

  @Test
  public void getScheduleByIdTest() {
    DTSchedule dts = new DTSchedule();
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);
    when(schedule.getDTSchedule()).thenReturn(dts);

    scheduleData.getScheduleById(1L);
  }

  @Test
  public void deleteScheduleTest() {
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);

    scheduleData.deleteSchedule(1L);
  }

  @Test
  public void listSchedulesTest() {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    ls.add(schedule);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(ls);
    when(schedule.getDTSchedule()).thenReturn(dts);

    scheduleData.listSchedules();
  }

  @Test
  public void SchedulesbyVCandVPTest() {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    ls.add(schedule);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("idVC", 1L)).thenReturn(typedQuery);
    when(typedQuery.setParameter("idVP", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(ls);
    when(schedule.getDTSchedule()).thenReturn(dts);
    when(schedule.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(schedule.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getDTVaccinationPlan()).thenReturn(new DTVaccinationPlan());
    when(schedule.isSunday()).thenReturn(true);
    when(schedule.isSaturday()).thenReturn(true);
    when(schedule.getStartTimeDaily()).thenReturn("Test");
    when(schedule.getEndTimeDaily()).thenReturn("Test");
    when(schedule.getStartDate()).thenReturn("Test");
    when(schedule.getEndDate()).thenReturn("Test");
    when(schedule.getFraction()).thenReturn(1);

    scheduleData.SchedulesbyVCandVP(1L, 1L);
  }

  @Test
  public void getSchedulesByPlanTest() {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    ls.add(schedule);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("idVP", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(ls);
    when(schedule.getDTSchedule()).thenReturn(dts);

    scheduleData.getSchedulesByPlan(1L);
  }

  @Test
  public void getNextSchedulesTest() {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    ls.add(schedule);
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("today", today)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(ls);
    when(schedule.getDTSchedule()).thenReturn(dts);
    when(schedule.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getId()).thenReturn(1L);
    when(schedule.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(schedule.isSaturday()).thenReturn(true);
    when(schedule.isSaturday()).thenReturn(true);

    scheduleData.getNextSchedules();
  }

  @Test
  public void unnasignScheduleTestReturnTrue() throws ParseException {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    List<Reservation> lR = new ArrayList<>();
    lR.add(reservation);
    ls.add(schedule);
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);
    when(schedule.getReservations()).thenReturn(lR);
    when(reservation.getDate()).thenReturn(today);

    scheduleData.unnasignSchedule(1L);
    Assert.assertEquals(true, scheduleData.unnasignSchedule(1L));
  }

  @Test
  public void unnasignScheduleTestReturnFalse() throws ParseException {
    DTSchedule dts = new DTSchedule();
    List<Schedule> ls = new ArrayList<>();
    List<Reservation> lR = new ArrayList<>();
    lR.add(reservation);
    ls.add(schedule);
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);
    when(schedule.getReservations()).thenReturn(lR);
    when(reservation.getDate()).thenReturn("2099-01-01");

    scheduleData.unnasignSchedule(1L);
    Assert.assertEquals(false, scheduleData.unnasignSchedule(1L));
  }
}
