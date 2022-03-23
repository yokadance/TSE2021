package DAL;

import DTO.DTDataSource;
import DTO.DTRestriction;
import entities.DataSource;
import entities.Restriction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceDataTest {

  private DataSourceData dataSourceData;

  private DataSource dataSource;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Restriction restriction;

  @Before
  public void setup() {
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    dataSource = mock(DataSource.class);
    restriction = mock(Restriction.class);
    this.dataSourceData = new DataSourceData();
    this.dataSourceData.setData(data);
    this.dataSourceData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(dataSource);
    Mockito.reset(restriction);
  }

  @Test
  public void saveDataSourceTest() {
    DTDataSource dtds = new DTDataSource();
    dtds.setId(1L);
    when(modelMapper.map(dtds, DataSource.class)).thenReturn(dataSource);
    when(dataSource.getId()).thenReturn(null);

    dataSourceData.saveDataSource(dtds);
  }

  @Test
  public void saveDataSourceTestWithIdNotNull() {
    DTDataSource dtds = new DTDataSource();
    dtds.setId(1L);
    when(modelMapper.map(dtds, DataSource.class)).thenReturn(dataSource);
    when(dataSource.getId()).thenReturn(1L);
    when(data.find(DataSource.class, 1L)).thenReturn(dataSource);
    when(dataSource.getCreateDate()).thenReturn(new Date());

    dataSourceData.saveDataSource(dtds);
  }

  @Test
  public void getDataSourceByIdTest() {
    DTDataSource dtds = new DTDataSource();
    dtds.setId(1L);
    when(data.find(DataSource.class, 1L)).thenReturn(dataSource);
    when(dataSource.getDTDataSource()).thenReturn(dtds);

    dataSourceData.getDataSourceById(1L);
  }

  @Test
  public void gdeleteDataSourceTest() {
    DTDataSource dtds = new DTDataSource();
    dtds.setId(1L);
    when(data.find(DataSource.class, 1L)).thenReturn(dataSource);
    when(dataSource.getDTDataSource()).thenReturn(dtds);
    when(modelMapper.map(dtds, DataSource.class)).thenReturn(dataSource);
    when(dataSource.getId()).thenReturn(1L);

    dataSourceData.deleteDataSource(1L);
  }

  @Test
  public void listDataSourcesTest() {
    List<DataSource> lDs = new ArrayList();
    lDs.add(dataSource);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lDs);
    when(dataSource.getDTDataSource()).thenReturn(new DTDataSource());

    dataSourceData.listDataSources();
  }

  @Test
  public void addRestrictionToDataSourceTest() {
    DTRestriction dtr = new DTRestriction();
    when(modelMapper.map(dtr, Restriction.class)).thenReturn(restriction);
    when(data.find(DataSource.class, 1L)).thenReturn(dataSource);

    dataSourceData.addRestrictionToDataSource(1L, dtr);
  }
}
