package Entities;

import DTO.DTPerson;
import entities.Person;
import enumerations.Sex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

  private Person person;

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
  public void personTest() {
    person =
      new Person(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeString,
        Sex.feminine,
        fakeDate,
        fakeString,
        new ArrayList<>()
      );
    person = new Person(new DTPerson());
    person = new Person(fakeLong, fakeString, fakeString, fakeString, Sex.masculine, fakeDate, fakeString, new ArrayList<>());
    person.getSurname();
    person.setSurname(fakeString);
    person.getSecondlastname();
    person.setSecondlastname(fakeString);
    person.getIdUruguay();
    person.setIdUruguay(fakeString);
    person.getRoles();
    person.setRoles(new ArrayList<>());
    person.getDTPerson();
    person.getDTBasicPerson();
  }
}
