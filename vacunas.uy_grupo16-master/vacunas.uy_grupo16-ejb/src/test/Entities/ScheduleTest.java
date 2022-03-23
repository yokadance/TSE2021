package Entities;

import entities.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {

  Schedule schedule;

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  @Before
  public void setup() {}

  @After
  public void teardown() {}

  @Test
  public void scheduleTest() {
    schedule =
      new Schedule(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeInt,
        true,
        true,
        new Authority(),
        new VaccinationPlan(),
        new ArrayList<>(),
        new VaccinationCenter()
      );
    schedule.getStartDate();
    schedule.setStartDate(fakeString);
    schedule.getEndDate();
    schedule.setEndDate(fakeString);
    schedule.getStartTimeDaily();
    schedule.setStartTimeDaily(fakeString);
    schedule.getEndTimeDaily();
    schedule.setEndTimeDaily(fakeString);
    schedule.getFraction();
    schedule.setFraction(fakeInt);
    schedule.isSaturday();
    schedule.setSaturday(true);
    schedule.isSunday();
    schedule.setSunday(true);
    schedule.getAuthority();
    schedule.setAuthority(new Authority());
    schedule.getVaccinationPlan();
    schedule.setVaccinationPlan(new VaccinationPlan());
    schedule.getReservations();
    schedule.setReservations(new ArrayList<>());
    schedule.getVaccinationCenter();
    schedule.setVaccinationCenter(new VaccinationCenter());
    schedule.getAssignment();
    schedule.setAssignment(new ArrayList<>());
    schedule.addReservation(new Reservation());
    schedule.getDTSchedule();
  }
}
