package Entities;

import entities.*;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentTest {

  private Assignment assignment;

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  private float fakeFloat;

  @Before
  public void setup() {}

  @After
  public void teardown() {}

  @Test
  public void AssignmentTest() {
    assignment = new Assignment();
    assignment = new Assignment(fakeString, fakeString, fakeString, fakeString, fakeString);
    assignment = new Assignment(fakeString, fakeString, fakeString, fakeString, new VaccinationPost(), new Vaccinator(), new Schedule());
    assignment.getPkId();
    assignment.setPkId(new AssignmentPK());
    assignment.getStartDate();
    assignment.setStartDate(fakeString);
    assignment.getEndDate();
    assignment.setEndDate(fakeString);
    assignment.getStartTime();
    assignment.setStartTime(fakeString);
    assignment.getEndTime();
    assignment.setEndTime(fakeString);
    assignment.getVaccinationPost();
    assignment.setVaccinationPost(new VaccinationPost());
    assignment.getVaccinator();
    assignment.setVaccinator(new Vaccinator());
    assignment.getSchedule();
    assignment.setSchedule(new Schedule());
  }
}
