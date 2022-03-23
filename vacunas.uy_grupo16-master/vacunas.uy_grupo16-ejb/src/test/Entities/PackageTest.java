package Entities;

import entities.Batch;
import entities.Package;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import enumerations.PackageState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class PackageTest {

  Package aPackage;

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
  public void aPackageTest() {
    aPackage = new Package();
    aPackage = new Package(fakeLong, fakeLong);
    aPackage = new Package(fakeLong, fakeLong, PackageState.Pending);
    aPackage =
      new Package(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeLong,
        fakeLong,
        PackageState.Pending,
        new ArrayList<>(),
        new Batch(),
        new ArrayList<>(),
        new VaccinationPlan(),
        new VaccinationCenter()
      );
    aPackage.getPackageState();
    aPackage.setPackageState(PackageState.Pending);
    aPackage.getPackageNumber();
    aPackage.setPackageNumber(fakeLong);
    //        aPackage.getQuantity();
    aPackage.setQuantity(fakeLong);
    aPackage.getVaccinationActs();
    aPackage.setVaccinationActs(new ArrayList<>());
    aPackage.getBatch();
    aPackage.setBatch(new Batch());
    aPackage.getVaccination();
    aPackage.setVaccination(new ArrayList<>());
    aPackage.getVaccinationPlan();
    aPackage.setVaccinationPlan(new VaccinationPlan());
    aPackage.getVaccinationCenter();
    aPackage.setVaccinationCenter(new VaccinationCenter());
    aPackage.getDTPackage();
  }
}
