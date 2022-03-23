package Entities;

import entities.Batch;
import entities.Vaccine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class BatchTest {

  Batch batch;

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
  public void batchTest() {
    batch = new Batch();
    batch = new Batch(fakeLong, fakeInt, fakeDate, fakeInt, fakeInt);
    batch =
      new Batch(
        fakeLong,
        fakeDate,
        fakeDate,
        fakeDate,
        fakeString,
        fakeLong,
        fakeInt,
        fakeDate,
        fakeInt,
        fakeInt,
        new ArrayList<>(),
        new Vaccine()
      );
    batch.getNumber();
    batch.setNumber(fakeLong);
    batch.getQuantity();
    batch.setQuantity(fakeInt);
    batch.getCreationDate();
    batch.setCreationDate(fakeDate);
    batch.getExpirationDate();
    batch.setExpirationDate(fakeInt);
    batch.getAvailable();
    batch.setAvailable(fakeInt);
    batch.getaPackage();
    batch.setaPackage(new ArrayList<>());
    batch.getVaccine();
    batch.setVaccine(new Vaccine());
    batch.getDTBatch();
  }
}
