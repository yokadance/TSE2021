package Controllers;

import Controller.DataSourceController;
import IDAL.IDataSourceData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceTests {

  @Spy
  private IDataSourceData iDataSourceData;

  private DataSourceController dataSourceController;

  @Before
  public void setup() {
    this.iDataSourceData = mock(IDataSourceData.class);
    this.dataSourceController = new DataSourceController();
    this.dataSourceController.setiDataSourceData(iDataSourceData);
  }

  @After
  public void teardown() {
    Mockito.reset(iDataSourceData);
  }

  @Test
  public void saveDataSourceTest() {
    dataSourceController.saveDataSource(any());
    Mockito.verify(iDataSourceData, times(1)).saveDataSource(any());
  }

  @Test
  public void getDataSourceByIdTest() {
    dataSourceController.getDataSourceById(anyLong());
    Mockito.verify(iDataSourceData, times(1)).getDataSourceById(anyLong());
  }

  @Test
  public void deleteDataSourceTest() {
    dataSourceController.deleteDataSource(anyLong());
    Mockito.verify(iDataSourceData, times(1)).deleteDataSource(anyLong());
  }

  @Test
  public void listDataSourcesTest() {
    dataSourceController.listDataSources();
    Mockito.verify(iDataSourceData, times(1)).listDataSources();
  }

  @Test
  public void addRestrictionToDataSourceTest() {
    dataSourceController.addRestrictionToDataSource(anyLong(), any());
    Mockito.verify(iDataSourceData, times(1)).addRestrictionToDataSource(anyLong(), any());
  }
}
