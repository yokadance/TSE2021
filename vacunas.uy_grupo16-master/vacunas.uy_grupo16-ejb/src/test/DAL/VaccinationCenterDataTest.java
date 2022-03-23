package DAL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTVaccinationCenter;
import IController.IVaccinationPlanController;
import entities.VaccinationCenter;
import entities.VaccinationPlan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationCenterDataTest {

  private VaccinationCenterData vaccinationCenterData;

  private VaccinationCenter vaccinationCenter;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPlan vaccinationPlan;

  private IVaccinationPlanController iVaccinationPlanController;

  @Before
  public void setup() {
    vaccinationCenter = mock(VaccinationCenter.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    iVaccinationPlanController = mock(IVaccinationPlanController.class);
    this.vaccinationCenterData = new VaccinationCenterData();
    this.vaccinationCenterData.setData(data);
    this.vaccinationCenterData.setModelMapper(modelMapper);
    this.vaccinationCenterData.setiVaccinationPlanController(iVaccinationPlanController);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(iVaccinationPlanController);
  }

  @Test
  public void newVaccinationCenterTest() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);

    vaccinationCenterData.newVaccinationCenter(dtvc);
  }

  @Test
  public void saveVaccinationCenterTestUsingMerge() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> ldtvp = new ArrayList<>();
    ldtvp.add("1");
    dtvc.setVaccinationPlans(ldtvp);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getId()).thenReturn(1L); //cambiar aca para entrar al persist

    vaccinationCenterData.saveVaccinationCenter(dtvc);
  }

  @Test
  public void saveVaccinationCenterTestUsingPersist() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<String> ldtvp = new ArrayList<>();
    ldtvp.add("1");
    dtvc.setVaccinationPlans(ldtvp);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getId()).thenReturn(null); //cambiar aca para entrar al persist

    vaccinationCenterData.saveVaccinationCenter(dtvc);
  }

  @Test
  public void getVaccinationCenterByIdTestOk() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvc);

    vaccinationCenterData.getVaccinationCenterById(1L);
  }

  @Test
  public void getVaccinationCenterByIdTestReturningNull() { //averiguar cómo forzar el NoResultException
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);

    vaccinationCenterData.getVaccinationCenterById(1L);
  }

  @Test
  public void deleteVaccinationCenterTest() {
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);

    vaccinationCenterData.deleteVaccinationCenter(1L);
  }

  @Test
  public void listVaccinationsCentersTest() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lVc);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvc);

    vaccinationCenterData.listVaccinationsCenters();
  }

  @Test
  public void getCentersByPlanTest() {
    List<VaccinationPlan> lVp = new ArrayList<>();
    lVp.add(vaccinationPlan);
    vaccinationCenter.setVaccinationPlans(lVp);
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lVc);
    when(vaccinationCenter.getVaccinationPlans()).thenReturn(lVp);
    when(vaccinationPlan.getId()).thenReturn(1L);

    vaccinationCenterData.getCentersByPlan(1L);
  }

  @Test
  public void vaccinationCenterPasswordTest() {
    vaccinationCenterData.vaccinationCenterPassword();
  }

  @Test
  public void validatePasswordTestReturnTrue() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    dtvc.setPassword("Test");
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvc);
    when(vaccinationCenterData.getVaccinationCenterById(1L)).thenReturn(dtvc);

    vaccinationCenterData.validatePassword(1L, "Test");
    Assert.assertEquals(true, vaccinationCenterData.validatePassword(1L, "Test"));
  }

  @Test
  public void validatePasswordTestReturnFalse() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    dtvc.setPassword("Test2");
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvc);
    when(vaccinationCenterData.getVaccinationCenterById(1L)).thenReturn(dtvc);

    vaccinationCenterData.validatePassword(1L, "Test");
    Assert.assertEquals(false, vaccinationCenterData.validatePassword(1L, "Test"));
  }

  @Test
  public void getCentersAvailablesByPlanTest() {
    DTVaccinationCenter dtvp = new DTVaccinationCenter();
    List<VaccinationCenter> lV = new ArrayList<>();
    List<VaccinationPlan> lP = new ArrayList<>();
    lV.add(vaccinationCenter);
    lP.add(vaccinationPlan);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lV);
    when(vaccinationCenter.getVaccinationPlans()).thenReturn(lP);
    when(vaccinationPlan.getId()).thenReturn(2L);
    when(vaccinationCenter.getId()).thenReturn(1L);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvp);
    List<VaccinationPlan> lVp = new ArrayList<>();
    lVp.add(vaccinationPlan);
    vaccinationCenter.setVaccinationPlans(lVp);
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lVc);
    when(vaccinationCenter.getVaccinationPlans()).thenReturn(lVp);

    vaccinationCenterData.getCentersAvailablesByPlan(1L);
  }

  @Test
  public void unassignVaccinationCenterTest() {
    List<VaccinationCenter> lV = new ArrayList<>();
    List<VaccinationPlan> lP = new ArrayList<>();
    lV.add(vaccinationCenter);
    lP.add(vaccinationPlan);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getVaccinationPlans()).thenReturn(lP);
    when(vaccinationPlan.getId()).thenReturn(1L);

    vaccinationCenterData.unassignVaccinationCenter(1L, 1L);
  }
}
