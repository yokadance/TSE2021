package Entities;

import entities.Batch;
import entities.Disease;
import entities.Laboratory;
import entities.Vaccine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VaccineTest {

  private Vaccine vaccine;

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
  public void vaccineTest() {
    Batch batch = mock(Batch.class);
    Laboratory laboratory = mock(Laboratory.class);
    Disease disease = mock(Disease.class);
    Vaccine mockVacc = mock(Vaccine.class);
    vaccine =
      new Vaccine(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        fakeInt,
        fakeFloat,
        fakeInt,
        fakeInt,
        new ArrayList<>(),
        new ArrayList<>(),
        new Laboratory(),
        new Disease()
      );
    vaccine.getName();
    vaccine.setName(fakeString);
    vaccine.getDoseQuantity();
    vaccine.setDoseQuantity(fakeInt);
    vaccine.getTemperature();
    vaccine.setTemperature(fakeFloat);
    vaccine.getMonthQuantity();
    vaccine.setMonthQuantity(fakeInt);
    vaccine.getInmunity();
    vaccine.setInmunity(fakeInt);
    vaccine.getRestrictions();
    vaccine.setRestrictions(new ArrayList<>());
    vaccine.getLaboratory();
    vaccine.setLaboratory(laboratory);
    vaccine.getDisease();
    vaccine.setDisease(disease);
    vaccine.getBatches();
    vaccine.setBatches(new ArrayList<>());

    when(laboratory.getId()).thenReturn(fakeLong);
    when(disease.getId()).thenReturn(fakeLong);
    vaccine.getDTVaccine();
  }
}
