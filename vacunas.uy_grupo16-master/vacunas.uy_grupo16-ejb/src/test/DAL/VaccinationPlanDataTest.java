package DAL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import entities.*;
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
public class VaccinationPlanDataTest {

  private VaccinationPlanData vaccinationPlanData;

  private VaccinationPlan vaccinationPlan;

  private Schedule schedule;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Restriction restriction;

  private VaccinationCenter vaccinationCenter;

  private Vaccine vaccine;

  private Disease disease;

  @Before
  public void setup() {
    restriction = mock(Restriction.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    schedule = mock(Schedule.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    vaccine = mock(Vaccine.class);
    disease = mock(Disease.class);
    this.vaccinationPlanData = new VaccinationPlanData();
    this.vaccinationPlanData.setData(data);
    this.vaccinationPlanData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(schedule);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(restriction);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(vaccine);
    Mockito.reset(disease);
  }

  @Test
  public void saveVaccinationPlanTestOk() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<DTVaccinationCenter> lDtc = new ArrayList<>();
    lDtc.add(dtvc);
    when(modelMapper.map(dtp, VaccinationPlan.class)).thenReturn(vaccinationPlan);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(vaccinationPlan.getId()).thenReturn(1L);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getCreateDate()).thenReturn(new Date());

    vaccinationPlanData.saveVaccinationPlan(dtp, lDtc);
  }

  @Test
  public void saveVaccinationPlanTestWithVaccinationCenterEmpty() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<DTVaccinationCenter> lDtc = new ArrayList<>();
    when(modelMapper.map(dtp, VaccinationPlan.class)).thenReturn(vaccinationPlan);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(vaccinationPlan.getId()).thenReturn(1L);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getCreateDate()).thenReturn(new Date());

    vaccinationPlanData.saveVaccinationPlan(dtp, lDtc);
  }

  @Test
  public void saveVaccinationPlanTestWithVaccinationPlanIdNull() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<DTVaccinationCenter> lDtc = new ArrayList<>();
    lDtc.add(dtvc);
    VaccinationPlan vp2 = mock(VaccinationPlan.class);
    when(modelMapper.map(dtp, VaccinationPlan.class)).thenReturn(vaccinationPlan);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);
    when(vaccinationPlan.getId()).thenReturn(null);
    when(data.merge(vaccinationPlan)).thenReturn(vp2);
    when(vp2.getId()).thenReturn(1L);

    vaccinationPlanData.saveVaccinationPlan(dtp, lDtc);
  }

  @Test
  public void getVaccinationPlanByIdTest() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getDTVaccinationPlan()).thenReturn(dtp);

    vaccinationPlanData.getVaccinationPlanById(1L);
  }

  @Test
  public void deleteVaccinationPlanTest() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);

    vaccinationPlanData.deleteVaccinationPlan(1L);
  }

  @Test
  public void listVaccinationsPlansTest() {
    DTVaccinationPlan dtp = new DTVaccinationPlan();
    List<VaccinationPlan> lVp = new ArrayList<>();
    lVp.add(vaccinationPlan);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lVp);
    when(vaccinationPlan.getDTVaccinationPlan()).thenReturn(dtp);

    vaccinationPlanData.listVaccinationsPlans();
  }

  @Test
  public void vaccinationCentersByVaccinationPlanTest() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lVc);
    when(vaccinationCenter.getDTVaccinationCenter()).thenReturn(dtvc);

    vaccinationPlanData.vaccinationCentersByVaccinationPlan(1L);
  }

  @Test
  public void addCenterToPlanTest() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getVaccinationCenters()).thenReturn(lVc);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);

    vaccinationPlanData.addCenterToPlan(1L, 1L);
  }

  @Test
  public void addCenterToPlanTestWithVaccinationCentersEmpty() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getVaccinationCenters()).thenReturn(lVc);

    vaccinationPlanData.addCenterToPlan(1L, 1L);
  }

  @Test
  public void addRestrictionToPlanTestWithRestrictions() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    List<Restriction> lR = new ArrayList<>();
    lR.add(restriction);
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getRestriction()).thenReturn(lR);
    when(data.find(Restriction.class, 1L)).thenReturn(restriction);

    vaccinationPlanData.addRestrictionToPlan(1L, 1L);
  }

  @Test
  public void addRestrictionToPlanTestWithoutRestrictions() {
    List<Restriction> lR = new ArrayList<>();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getRestriction()).thenReturn(lR);
    when(data.find(Restriction.class, 1L)).thenReturn(restriction);

    vaccinationPlanData.addRestrictionToPlan(1L, 1L);
  }

  @Test
  public void unassignCenterOfPlanTest() {
    List<VaccinationCenter> lVc = new ArrayList<>();
    lVc.add(vaccinationCenter);
    List<Restriction> lR = new ArrayList<>();
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getVaccinationCenters()).thenReturn(lVc);
    when(vaccinationCenter.getId()).thenReturn(1L);

    vaccinationPlanData.unassignCenterOfPlan(1L, 1L);
  }

  @Test
  public void getDataMonVPlanTest() {
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getId()).thenReturn(1L);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(vaccinationPlan.getVaccine()).thenReturn(vaccine);
    when(vaccine.getName()).thenReturn("Test");
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");
    when(vaccinationPlan.getEndDate()).thenReturn("Test");
    when(vaccine.getDoseQuantity()).thenReturn(1);
    when(vaccinationPlan.getVaccineQuantity()).thenReturn(1);
    when(vaccinationPlan.getStartDate()).thenReturn("Test");

    vaccinationPlanData.getDataMonVPlan(1L);
  }
}
