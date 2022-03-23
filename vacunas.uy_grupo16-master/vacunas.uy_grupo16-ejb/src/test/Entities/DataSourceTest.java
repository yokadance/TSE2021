package Entities;

import entities.DataSource;
import entities.Restriction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceTest {

  DataSource dataSource;

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
  public void dataSourceTest() {
    dataSource = new DataSource();
    dataSource = new DataSource(fakeLong, fakeDate, fakeDate, fakeDate, fakeString, fakeString, fakeString, new Restriction());
    dataSource.getName();
    dataSource.setName(fakeString);
    dataSource.getUrl();
    dataSource.setUrl(fakeString);
    dataSource.getRestriction();
    dataSource.setRestriction(new Restriction());
    dataSource.getDTDataSource();
  }
}
