package Entities;

import entities.AssignmentPK;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentPKTest {

  AssignmentPK assignmentPK;

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
  public void assignmentPKTest() {
    assignmentPK = new AssignmentPK();
    assignmentPK.getId_vpost();
    assignmentPK.setId_vpost(fakeLong);
    assignmentPK.getId_vaccinator();
    assignmentPK.setId_vaccinator(fakeLong);
    assignmentPK.getId_schedule();
    assignmentPK.setId_schedule(fakeLong);
  }
}
