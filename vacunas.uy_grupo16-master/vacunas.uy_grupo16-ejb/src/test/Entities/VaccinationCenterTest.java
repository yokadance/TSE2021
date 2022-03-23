package Entities;

import entities.VaccinationCenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationCenterTest {

  private VaccinationCenter vaccinationCenter;

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
  public void vaccinationCenterTest() {
    vaccinationCenter =
      new VaccinationCenter(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>()
      );
    vaccinationCenter.setLongitude(fakeString);
    vaccinationCenter.setLatitude(fakeString);
    vaccinationCenter.setPassword(fakeString);
    vaccinationCenter.setName(fakeString);
    vaccinationCenter.setLocation(fakeString);
    vaccinationCenter.setVaccinationPosts(new ArrayList<>());
    vaccinationCenter.setVaccinations(new ArrayList<>());
    vaccinationCenter.setVaccinationPlans(new ArrayList<>());
    vaccinationCenter.setSchedules(new ArrayList<>());
    vaccinationCenter.setPackages(new ArrayList<>());
  }
}
