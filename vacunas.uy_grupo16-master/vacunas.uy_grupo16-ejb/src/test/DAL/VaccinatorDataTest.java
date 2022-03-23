package DAL;

import DTO.DTPerson;
import DTO.DTVaccinationPost;
import DTO.DTVaccinator;
import entities.*;
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
public class VaccinatorDataTest {

  private VaccinatorData vaccinatorData;

  private Vaccinator vaccinator;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPost vaccinationPost;

  private Assignment assignment;

  private Person person;

  private Role role;

  private VaccinationPlan vaccinationPlan;

  private Schedule schedule;

  private VaccinationCenter vaccinationCenter;

  @Before
  public void setup() {
    vaccinationCenter = mock(VaccinationCenter.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    person = mock(Person.class);
    assignment = mock(Assignment.class);
    role = mock(Role.class);
    vaccinationPost = mock(VaccinationPost.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    schedule = mock(Schedule.class);
    vaccinator = mock(Vaccinator.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.vaccinatorData = new VaccinatorData();
    this.vaccinatorData.setData(data);
    this.vaccinatorData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(vaccinator);
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(role);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(person);
    Mockito.reset(schedule);
    Mockito.reset(vaccinationPost);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveVaccinatorTestWithVaccinatorDeleted() {
    DTVaccinator dtv = new DTVaccinator();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtv.setDtPerson(dtp);
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(roles);
    when(role.getId()).thenReturn(1L);
    when(role.getName()).thenReturn("vaccinator");
    when(role.getDeleteDate()).thenReturn(new Date());
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);

    vaccinatorData.saveVaccinator(dtv);
  }

  @Test
  public void saveVaccinatorTestWithVaccinatorNotDeleted() {
    DTVaccinator dtv = new DTVaccinator();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtv.setDtPerson(dtp);
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(roles);
    when(role.getId()).thenReturn(1L);
    when(role.getName()).thenReturn("vaccinator");
    when(role.getDeleteDate()).thenReturn(null);
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);

    vaccinatorData.saveVaccinator(dtv);
  }

  @Test
  public void saveVaccinatorTestWithVaccinatorNotExist() {
    DTVaccinator dtv = new DTVaccinator();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtv.setDtPerson(dtp);
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(roles);
    when(role.getId()).thenReturn(1L);
    when(role.getName()).thenReturn("Test");
    when(role.getDeleteDate()).thenReturn(null);
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);

    vaccinatorData.saveVaccinator(dtv);
  }

  @Test
  public void getVaccinatorsTest() {
    List<Vaccinator> lV = new ArrayList<>();
    lV.add(vaccinator);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lV);

    vaccinatorData.getVaccinators();
  }

  @Test
  public void getByIdVaccinatorTest() {
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);

    vaccinatorData.getByIdVaccinator(1L);
  }

  @Test
  public void deleteVaccinatorTest() {
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);

    vaccinatorData.deleteVaccinator(1L);
  }

  @Test
  public void setVCtoVTestOk() {
    DTVaccinationPost dtVp = new DTVaccinationPost();
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);
    when(modelMapper.map(dtVp, VaccinationPost.class)).thenReturn(vaccinationPost);

    vaccinatorData.setVCtoV(1L, dtVp);
  }

  @Test
  public void setVCtoVTestWithException() {
    DTVaccinationPost dtVp = null;
    when(data.find(Vaccinator.class, 1L)).thenReturn(null);
    when(modelMapper.map(dtVp, VaccinationPost.class)).thenReturn(null);

    vaccinatorData.setVCtoV(null, null);
  }

  @Test
  public void getVaccinatorDatabyCiTest() {
    DTVaccinationPost dtVp = null;
    String ci = "Test";
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    vaccinator.setAssignment(lA);
    when(data.createQuery("select v from Vaccinator v where v.person.ci= :ci and v.deleteDate is null", Vaccinator.class))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(vaccinator);
    when(vaccinator.getAssignment()).thenReturn(lA);
    when(assignment.getSchedule()).thenReturn(schedule);
    when(schedule.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getId()).thenReturn(1L);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(assignment.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getId()).thenReturn(1L);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(vaccinationPost.getId()).thenReturn(1L);
    when(vaccinationPost.getName()).thenReturn("Test");
    when(assignment.getStartDate()).thenReturn("Test");
    when(assignment.getEndDate()).thenReturn("Test");
    when(assignment.getStartTime()).thenReturn("Test");
    when(assignment.getEndTime()).thenReturn("Test");

    vaccinatorData.getVaccinatorDatabyCi(ci);
  }
}
