package Entities;

import entities.Authority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityTest {

  private Authority authority;

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
  public void authorityTest() {
    authority =
      new Authority(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>()
      );
    authority.getVaccinationPlans();
    authority.setVaccinationPlans(new ArrayList<>());
    authority.getSchedules();
    authority.setSchedules(new ArrayList<>());
  }
}
