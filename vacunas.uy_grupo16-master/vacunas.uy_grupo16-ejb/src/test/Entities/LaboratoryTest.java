package Entities;

import DTO.DTLaboratory;
import entities.Laboratory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class LaboratoryTest {

  Laboratory laboratory;

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
  public void laboratoryTest() {
    laboratory = new Laboratory();
    laboratory =
      new Laboratory(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, fakeString, fakeString, new ArrayList<>());
    laboratory = new Laboratory(new DTLaboratory());
    laboratory.getName();
    laboratory.setName(fakeString);
    laboratory.getOrigin();
    laboratory.setOrigin(fakeString);
    laboratory.getEmail();
    laboratory.setEmail(fakeString);
    laboratory.getPhone();
    laboratory.setPhone(fakeString);
    laboratory.getVaccine();
    laboratory.setVaccine(new ArrayList<>());
    laboratory.getDTLaboratory();
  }
}
