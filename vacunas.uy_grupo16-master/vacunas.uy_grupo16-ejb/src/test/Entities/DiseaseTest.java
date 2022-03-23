package Entities;

import entities.Disease;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DiseaseTest {

  Disease disease;

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
  public void diseaseTest() {
    disease = new Disease();
    disease = new Disease(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, new ArrayList<>());
    disease.getName();
    disease.setName(fakeString);
    disease.getSymptons();
    disease.setSymptons(fakeString);
    disease.getVaccine();
    disease.setVaccine(new ArrayList<>());
    disease.getDTDisease();
  }
}
