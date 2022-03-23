package Entities;

import entities.Citizen;
import entities.Package;
import entities.VaccinationAct;
import entities.VaccinationPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationActTest {

  private VaccinationAct vaccinationAct;

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
  public void vaccinationActTest() {
    vaccinationAct = new VaccinationAct();
    vaccinationAct =
      new VaccinationAct(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeString,
        new Citizen(),
        new VaccinationPost(),
        new Package()
      );
    vaccinationAct.getObservation();
    vaccinationAct.setObservation(fakeString);
    vaccinationAct.getCitizen();
    vaccinationAct.setCitizen(new Citizen());
    vaccinationAct.getVaccinationPost();
    vaccinationAct.setVaccinationPost(new VaccinationPost());
    vaccinationAct.getaPackage();
    vaccinationAct.setaPackage(new Package());
    vaccinationAct.getDTVaccinationAct();
  }
}
