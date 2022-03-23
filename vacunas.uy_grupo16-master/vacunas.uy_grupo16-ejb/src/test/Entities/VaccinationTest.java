package Entities;

import entities.Package;
import entities.Vaccination;
import entities.VaccinationCenter;
import enumerations.VaccinationState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationTest {

  private Vaccination vaccination;

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
  public void vaccinationTest() {
    vaccination = new Vaccination();
    vaccination =
      new Vaccination(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeDate,
        VaccinationState.Destroyed,
        new VaccinationCenter(),
        new Package()
      );
    vaccination.getState();
    vaccination.setState(VaccinationState.Destroyed);
    vaccination.getVaccinationCenter();
    vaccination.setVaccinationCenter(new VaccinationCenter());
    vaccination.getaPackage();
    vaccination.setaPackage(new Package());
    vaccination.getDTVaccination();
  }
}
