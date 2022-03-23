package Entities;

import entities.VaccinationCenter;
import entities.VaccinationPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationPostTest {

  private VaccinationPost vaccinationPost;

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
  public void vaccinationPostTest() {
    vaccinationPost =
      new VaccinationPost(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new VaccinationCenter(),
        new ArrayList<>(),
        new ArrayList<>()
      );
    vaccinationPost.setName(fakeString);
    vaccinationPost.setObservation(fakeString);
    vaccinationPost.setObservation(fakeString);
    vaccinationPost.getAssignments();
    vaccinationPost.setAssignments(new ArrayList<>());
    vaccinationPost.getVaccinationCenter();
    vaccinationPost.setVaccinationCenter(new VaccinationCenter());
    vaccinationPost.getReservations();
    vaccinationPost.setReservations(new ArrayList<>());
    vaccinationPost.getVaccinationActs();
    vaccinationPost.setVaccinationActs(new ArrayList<>());
  }
}
