package Entities;

import entities.Authority;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import entities.Vaccine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationPlanTest {

  private VaccinationPlan vaccinationPlan;

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
  public void vaccinationPlanTest() {
    vaccinationPlan =
      new VaccinationPlan(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeInt,
        new Authority(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        new Vaccine()
      );
    vaccinationPlan.setName(fakeString);
    vaccinationPlan.setStartDate(fakeString);
    vaccinationPlan.setEndDate(fakeString);
    vaccinationPlan.setEndDate(fakeString);
    vaccinationPlan.setVaccineQuantity(fakeInt);
    vaccinationPlan.setAuthority(new Authority());
    vaccinationPlan.setVaccinationCenters(new ArrayList<>());
    vaccinationPlan.setSchedule(new ArrayList<>());
    vaccinationPlan.setRestriction(new ArrayList<>());
    vaccinationPlan.setaPackage(new ArrayList<>());
    vaccinationPlan.addVaccinationCenter(new VaccinationCenter());
    vaccinationPlan.setVaccine(new Vaccine());
  }
}
