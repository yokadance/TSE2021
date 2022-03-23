package Entities;

import entities.BaseModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class BaseModelTest {

  private BaseModel baseModel;

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
  public void baseModelTest() {
    baseModel = new BaseModel(fakeLong, fakeString) {};
    baseModel.prePersist();
    baseModel.setId(fakeLong);
    baseModel.getCreateDate();
    baseModel.setCreateDate(fakeDate);
    baseModel.getUpdateDate();
    baseModel.setUpdateDate(fakeDate);
    baseModel.getDeleteDate();
    baseModel.setDeleteDate(fakeDate);
    baseModel.getUserid();
    baseModel.setUserid(fakeString);
  }
}
