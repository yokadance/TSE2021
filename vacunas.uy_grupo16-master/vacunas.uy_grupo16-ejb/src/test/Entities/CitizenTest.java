package Entities;

import entities.Citizen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class CitizenTest {

  Citizen citizen;

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
  public void citizenTest() {
    citizen =
      new Citizen(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>(),
        fakeString
      );
    citizen.getVaccinationActs();
    citizen.setVaccinationActs(new ArrayList<>());
    citizen.getReservations();
    citizen.setReservations(new ArrayList<>());
    citizen.getToken();
    citizen.setToken(fakeString);
  }
}
