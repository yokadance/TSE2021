package Entities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTLogistic;
import entities.LogisticPartner;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class LogisticPartnerTest {

  LogisticPartner logisticPartner;

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
  public void logisticPartnerTest() {
    logisticPartner =
      new LogisticPartner(
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
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>()
      );
    logisticPartner =
      new LogisticPartner(
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>()
      );
    logisticPartner.setName(fakeString);
    logisticPartner.setBusinessName(fakeString);
    logisticPartner.setRut(fakeString);
    logisticPartner.setPhone(fakeString);
    logisticPartner.setEmail(fakeString);
    logisticPartner.setReference(fakeString);
    logisticPartner.setIot(new ArrayList<>());
    logisticPartner.setLogistics(new ArrayList<>());
    logisticPartner.getPassword();
    logisticPartner.setPassword(fakeString);
    logisticPartner.setPackages(new ArrayList<>());
  }
}
