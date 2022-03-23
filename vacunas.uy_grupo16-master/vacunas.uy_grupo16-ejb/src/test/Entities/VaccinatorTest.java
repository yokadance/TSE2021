package Entities;

import entities.Person;
import entities.Vaccinator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinatorTest {

  private Vaccinator vaccinator;

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
  public void vaccinatorTest() {
    vaccinator = new Vaccinator(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, new Person(), new ArrayList<>());
    vaccinator.getDTVaccinator();
    vaccinator.setAssignment(new ArrayList<>());
  }
}
