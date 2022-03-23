package Entities;

import entities.Administrator;
import entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class AdministratorTest {

  private Administrator administrator;

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
  public void administratorTest() {
    administrator = new Administrator(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, new Person());
  }
}
