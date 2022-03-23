package Entities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import entities.DataSource;
import entities.Restriction;
import entities.Vaccine;
import enumerations.LogicOp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestrictionTest {

  Restriction restriction;

  private String fakeString = "Test";

  private Date fakeDate = new Date();

  private Long fakeLong = 1L;

  private int fakeInt = 1;

  @Before
  public void setup() {}

  @After
  public void teardown() {}

  @Test
  public void eventTest() {
    restriction =
      new Restriction(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        LogicOp.lesserThan,
        fakeInt,
        fakeString,
        new ArrayList<>(),
        new ArrayList<>(),
        new DataSource()
      );
    restriction.setElementName(fakeString);
    restriction.setLogicOperator(LogicOp.lesserThan);
    restriction.setValue(fakeInt);
    restriction.setVaccines(new ArrayList<>());
    restriction.setVaccinationPlans(new ArrayList<>());
    restriction.setDataSource(new DataSource());
    restriction.setDescription(fakeString);
    restriction.getDescription();
  }
}
