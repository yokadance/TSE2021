package DAL;

import DTO.DTAssignment;
import DTO.DTSchedule;
import DTO.DTVaccinationPost;
import DTO.DTVaccinator;
import entities.*;
import org.junit.After;
import org.junit.Assert;
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
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentDataTest {

  private AssignmentData assignmentData;

  private Assignment assignment;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPost vaccinationPost;

  private Schedule schedule;

  private Vaccinator vaccinator;

  private AssignmentPK assignmentPK;

  private Person person;

  @Before
  public void setup() {
    vaccinationPost = mock(VaccinationPost.class);
    assignmentPK = mock(AssignmentPK.class);
    vaccinator = mock(Vaccinator.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    schedule = mock(Schedule.class);
    data = mock(EntityManager.class);
    assignment = mock(Assignment.class);
    modelMapper = mock(ModelMapper.class);
    person = mock(Person.class);
    this.assignmentData = new AssignmentData();
    this.assignmentData.setData(data);
    this.assignmentData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(assignment);
    Mockito.reset(modelMapper);
    Mockito.reset(schedule);
    Mockito.reset(assignmentPK);
    Mockito.reset(vaccinator);
    Mockito.reset(vaccinationPost);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(person);
  }

  @Test
  public void saveAssignmentTestOk() {
    DTAssignment dta = new DTAssignment();
    when(modelMapper.map(dta, Assignment.class)).thenReturn(assignment);
    when(assignment.getPkId()).thenReturn(assignmentPK);
    when(assignmentPK.getId_schedule()).thenReturn(1L);
    when(assignmentPK.getId_vaccinator()).thenReturn(1L);
    when(assignmentPK.getId_vpost()).thenReturn(1L);
    when(data.find(Schedule.class, 1L)).thenReturn(schedule);
    when(data.find(Vaccinator.class, 1L)).thenReturn(vaccinator);
    when(data.find(VaccinationPost.class, 1L)).thenReturn(vaccinationPost);

    assignmentData.saveAssignment(dta);
  }

  @Test
  public void saveAssignmentTestWithException() {
    DTAssignment dta = new DTAssignment();
    when(modelMapper.map(dta, Assignment.class)).thenReturn(assignment);
    when(assignment.getPkId()).thenReturn(null);

    assignmentData.saveAssignment(dta);
  }

  @Test
  public void deleteAssignmentTestOk() {
    when(data.find(Assignment.class, 1L)).thenReturn(assignment);

    assignmentData.deleteAssignment(1L);
  }

  @Test
  public void deleteAssignmentTestWithException() {
    assignmentData.deleteAssignment(null);
  }

  @Test
  public void getAssignmentsTest() {
    List<DTAssignment> lDta = new ArrayList<>();
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    DTAssignment dta = new DTAssignment();
    String fakeString = "Test";
    dta.setStartDate(fakeString);
    dta.setEndDate(fakeString);
    dta.setStartTime(fakeString);
    dta.setEndTime(fakeString);
    lDta.add(dta);
    when(data.createQuery("select a from Assignment a where a.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lA);

    assignmentData.getAssignments();
  }

  @Test
  public void getAssignmentByIdTest() {
    DTAssignment dta = new DTAssignment();
    String fakeString = "Test";
    dta.setStartDate(fakeString);
    dta.setEndDate(fakeString);
    dta.setStartTime(fakeString);
    dta.setEndTime(fakeString);
    when(data.find(Assignment.class, 1L)).thenReturn(assignment);
    when(assignment.getSchedule()).thenReturn(schedule);
    when(schedule.getDTSchedule()).thenReturn(new DTSchedule());
    when(assignment.getVaccinator()).thenReturn(vaccinator);
    when(vaccinator.getDTVaccinator()).thenReturn(new DTVaccinator());
    when(assignment.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getDTVaccinationPost()).thenReturn(new DTVaccinationPost());

    assignmentData.getAssignmentById(1L);
  }

  @Test
  public void unassignAssignmentTestWithoutException() {
    DTAssignment dta = new DTAssignment();
    when(data.find(Assignment.class, 1L)).thenReturn(assignment);

    assignmentData.unassignAssignment(1L);
  }

  @Test
  public void unassignAssignmentTestWithException() {
    assignmentData.unassignAssignment(null);
  }

  @Test
  public void getAssignmentsByPostTest() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    DTAssignment dta = new DTAssignment();
    when(data.createQuery("select a from Assignment a where a.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lA);
    when(assignment.getPkId()).thenReturn(new AssignmentPK());
    when(assignment.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getId()).thenReturn(1L);
    when(assignment.getStartTime()).thenReturn("2021-01-01");
    when(assignment.getEndTime()).thenReturn("2021-01-01");
    when(assignment.getStartTime()).thenReturn("00:00");
    when(assignment.getEndTime()).thenReturn("00:00");

    assignmentData.getAssignmentsByPost(1L);
  }

  @Test
  public void getVaccinatorByDateTestWithoutException() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    when(
      data.createQuery(
        "select v from Assignment v where v.vaccinator.person.ci= :ci and ((:dateEnd between v.startDate and v.endDate) or (:dateStart between v.startDate and v.endDate)) ",
        Assignment.class
      )
    )
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.setParameter("dateStart", "Test")).thenReturn(typedQuery);
    when(typedQuery.setParameter("dateEnd", "Test")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lA);
    when(assignment.getStartDate()).thenReturn("2021-01-01");
    when(assignment.getEndDate()).thenReturn("2021-01-01");
    when(assignment.getStartTime()).thenReturn("00:00");
    when(assignment.getEndTime()).thenReturn("00:00");

    assignmentData.getVaccinatorByDate("Test", "Test", "Test");
  }

  @Test
  public void getVaccinatorByDateTestWithException() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    when(
      data.createQuery(
        "select v from Assignment v where v.vaccinator.person.ci= :ci and ((:dateEnd between v.startDate and v.endDate) or (:dateStart between v.startDate and v.endDate)) ",
        Assignment.class
      )
    )
      .thenReturn(null);

    assignmentData.getVaccinatorByDate("Test", "Test", "Test");
  }

  @Test
  public void checkVaccinatorAvailabilityTest() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    when(
      data.createQuery(
        "select v from Assignment v where v.vaccinator.person.ci= :ci and ((:dateEnd between v.startDate and v.endDate) or (:dateStart between v.startDate and v.endDate)) ",
        Assignment.class
      )
    )
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.setParameter("dateStart", "Test")).thenReturn(typedQuery);
    when(typedQuery.setParameter("dateEnd", "Test")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lA);
    when(assignment.getStartTime()).thenReturn("2021-01-01");
    when(assignment.getEndTime()).thenReturn("2021-01-01");
    when(assignment.getStartTime()).thenReturn("00:00");
    when(assignment.getEndTime()).thenReturn("00:00");

    assignmentData.checkVaccinatorAvailability("Test", "Test", "Test");
    Assert.assertEquals(false, assignmentData.checkVaccinatorAvailability("Test", "Test", "Test"));
  }

  @Test
  public void getVaccinatorsOnPostsTestWithoutException() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    when(data.createQuery("select a from Assignment a where a.schedule.vaccinationCenter.id=:vCenterId", Assignment.class))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("vCenterId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lA);
    when(assignment.getStartDate()).thenReturn("2021-01-01");
    when(assignment.getVaccinator()).thenReturn(vaccinator);
    when(vaccinator.getPerson()).thenReturn(person);
    when(person.getName()).thenReturn("Test");
    when(person.getLastname()).thenReturn("Test");
    when(assignment.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getName()).thenReturn("Test");

    assignmentData.getVaccinatorsOnPosts(1L);
  }

  @Test
  public void getVaccinatorsOnPostsTestWithException() {
    List<Assignment> lA = new ArrayList<>();
    lA.add(assignment);
    when(data.createQuery("select a from Assignment a where a.schedule.vaccinationCenter.id=:vCenterId", Assignment.class))
      .thenReturn(null);

    assignmentData.getVaccinatorsOnPosts(1L);
  }
}
