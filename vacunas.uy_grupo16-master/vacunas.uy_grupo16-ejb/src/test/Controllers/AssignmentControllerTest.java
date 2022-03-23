package Controllers;

import Controller.AssignmentController;
import IDAL.IAssignmentData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentControllerTest {

  @Spy
  private IAssignmentData iAssignmentData;

  private AssignmentController assignmentController;

  @Before
  public void setup() {
    this.iAssignmentData = mock(IAssignmentData.class);
    this.assignmentController = new AssignmentController();
    this.assignmentController.setiAssignmentData(iAssignmentData);
  }

  @After
  public void teardown() {
    Mockito.reset(iAssignmentData);
  }

  @Test
  public void saveAssignmentTest() {
    assignmentController.saveAssignment(any());
    Mockito.verify(iAssignmentData, times(1)).saveAssignment(any());
  }

  @Test
  public void deleteAssignmentTest() {
    assignmentController.deleteAssignment(any());
    Mockito.verify(iAssignmentData, times(1)).deleteAssignment(any());
  }

  @Test
  public void getAssignmentsTest() {
    assignmentController.getAssignments();
    Mockito.verify(iAssignmentData, times(1)).getAssignments();
  }

  @Test
  public void getAssignmentByIdTest() {
    assignmentController.getAssignmentById(1L);
    Mockito.verify(iAssignmentData, times(1)).getAssignmentById(1L);
  }

  @Test
  public void getAssignmentsByPostTest() {
    assignmentController.getAssignmentsByPost(1L);
    Mockito.verify(iAssignmentData, times(1)).getAssignmentsByPost(1L);
  }

  @Test
  public void getVaccinatorByDateTest() {
    assignmentController.getVaccinatorByDate("Test", "Test", "Test");
    Mockito.verify(iAssignmentData, times(1)).getVaccinatorByDate("Test", "Test", "Test");
  }

  @Test
  public void unassignAssignmentTest() {
    assignmentController.unassignAssignment(1L);
    Mockito.verify(iAssignmentData, times(1)).unassignAssignment(1L);
  }

  @Test
  public void checkVaccinatorAvailabilityTest() {
    assignmentController.checkVaccinatorAvailability("Test", "Test", "Test");
    Mockito.verify(iAssignmentData, times(1)).checkVaccinatorAvailability("Test", "Test", "Test");
  }

  @Test
  public void getVaccinatorsOnPostsTest() {
    assignmentController.getVaccinatorsOnPosts(1L);
    Mockito.verify(iAssignmentData, times(1)).getVaccinatorsOnPosts(1L);
  }
}
