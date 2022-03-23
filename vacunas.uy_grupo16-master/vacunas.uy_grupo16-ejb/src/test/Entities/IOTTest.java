package Entities;

import entities.IOT;
import entities.LogisticPartner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class IOTTest {

  private IOT iot;

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
  public void eventTestTest() {
    iot = new IOT();
    iot = new IOT(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeDate, new LogisticPartner(), fakeString);
    iot.getMessage();
    iot.setMessage(fakeString);
    iot.getLogisticPartner();
    iot.setLogisticPartner(new LogisticPartner());
    iot.getDTIot();
  }
}
