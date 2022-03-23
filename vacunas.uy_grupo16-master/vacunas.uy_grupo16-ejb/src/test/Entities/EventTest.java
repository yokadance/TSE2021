package Entities;

import entities.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class EventTest {

  Event event;

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
  public void eventTest() {
    event = new Event() {};
    event = new Event(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeDate) {};
    event.getDate();
    event.setDate(fakeDate);
    event.getDTEvent();
  }
}
