package DAL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTRestriction;
import entities.DataSource;
import entities.Restriction;
import entities.VaccinationPlan;
import enumerations.LogicOp;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class RestrictionDataTest {

  private RestrictionData restrictionData;

  private Restriction restriction;

  private DataSource dataSource;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPlan vaccinationPlan;

  @Before
  public void setup() {
    vaccinationPlan = mock(VaccinationPlan.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    restriction = mock(Restriction.class);
    modelMapper = mock(ModelMapper.class);
    dataSource = mock(DataSource.class);
    this.restrictionData = new RestrictionData();
    this.restrictionData.setData(data);
    this.restrictionData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(dataSource);
  }

  @Test
  public void saveRestrictionTestWithId() {
    DTRestriction dtr = new DTRestriction();
    when(modelMapper.map(dtr, Restriction.class)).thenReturn(restriction);
    when(restriction.getId()).thenReturn(1L);
    when(restriction.getCreateDate()).thenReturn(new Date());
    when(data.find(Restriction.class, 1L)).thenReturn(restriction);

    restrictionData.saveRestriction(dtr);
  }

  @Test
  public void saveRestrictionTestWithoutId() {
    DTRestriction dtr = new DTRestriction();
    when(modelMapper.map(dtr, Restriction.class)).thenReturn(restriction);
    when(restriction.getId()).thenReturn(null);

    restrictionData.saveRestriction(dtr);
  }

  @Test
  public void getRestrictionByIdTest() {
    when(data.find(Restriction.class, 1L)).thenReturn(restriction);
    when(restriction.getDTRestriction()).thenReturn(new DTRestriction());

    restrictionData.getRestrictionById(1L);
  }

  @Test
  public void deleteRestrictionTest() {
    when(data.find(Restriction.class, 1L)).thenReturn(restriction);

    restrictionData.deleteRestriction(1L);
  }

  @Test
  public void listRestrictionsTest() {
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    when(data.createQuery("select r from Restriction r where r.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lR);
    when(restriction.getDTRestriction()).thenReturn(new DTRestriction());

    restrictionData.listRestrictions();
  }

  @Test
  public void getRestrictionsByPlanTest() {
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    List<VaccinationPlan> lVp = new ArrayList<>();
    lVp.add(vaccinationPlan);
    when(data.createQuery("select r from Restriction r where r.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lR);
    when(restriction.getVaccinationPlans()).thenReturn(lVp);
    when(vaccinationPlan.getId()).thenReturn(1L);

    restrictionData.getRestrictionsByPlan(1L);
  }

  @Test
  public void getRestrictionByDataTest() {
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    when(data.createQuery("select r from Restriction r where r.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lR);
    when(restriction.getDataSource()).thenReturn(dataSource);
    when(dataSource.getId()).thenReturn(1L);

    restrictionData.getRestrictionByData(1L);
  }

  @Test
  public void getRestrictionsByVaccinationPlanTest() {
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getRestriction()).thenReturn(lR);
    when(restriction.getDTRestriction()).thenReturn(new DTRestriction());

    restrictionData.getRestrictionsByVaccinationPlan(1L);
  }

  @Test
  public void callAgeRestrictionApiTestOk() throws IOException {
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getRestriction()).thenReturn(lR);
    when(restriction.getDataSource()).thenReturn(dataSource);
    when(dataSource.getUrl()).thenReturn("https://vacunatorio1.azurewebsites.net/medicalinfo-0.0.1-SNAPSHOT/restrictions");
    when(restriction.getElementName()).thenReturn("Test");
    when(restriction.getLogicOperator()).thenReturn(LogicOp.equalTo);

    restrictionData.callAgeRestrictionApi(1, 1L);
  }
}
