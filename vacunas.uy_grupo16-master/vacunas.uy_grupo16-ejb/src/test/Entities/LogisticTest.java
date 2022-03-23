package Entities;

import entities.Logistic;
import entities.LogisticPartner;
import enumerations.BatchState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class LogisticTest {

  Logistic logistic;

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
  public void logisticTest() {
    logistic = new Logistic();
    logistic = new Logistic(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeDate, new LogisticPartner(), BatchState.Storaged);
    logistic.getTypeEvent();
    logistic.setTypeEvent(BatchState.Storaged);
    logistic.getLogisticPartner();
    logistic.setLogisticPartner(new LogisticPartner());
    logistic.getDTLogistic();
  }
}
