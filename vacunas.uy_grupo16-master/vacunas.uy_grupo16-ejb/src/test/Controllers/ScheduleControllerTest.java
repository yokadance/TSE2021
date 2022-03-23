package Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import Controller.ScheduleController;
import IDAL.IScheduleData;
import java.text.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleControllerTest {

  @Spy
  private IScheduleData iScheduleData;

  private ScheduleController scheduleController;

  @Before
  public void setup() {
    this.scheduleController = mock(ScheduleController.class);
    this.scheduleController = new ScheduleController();
    this.scheduleController.setiScheduleData(iScheduleData);
  }

  @After
  public void teardown() {
    Mockito.reset(iScheduleData);
  }

  @Test
  public void listSchedulesTest() {
    scheduleController.listSchedules();
    Mockito.verify(iScheduleData, times(1)).listSchedules();
  }

  @Test
  public void saveScheduleTest() {
    scheduleController.saveSchedule(any());
    Mockito.verify(iScheduleData, times(1)).saveSchedule(any());
  }

  @Test
  public void deleteScheduleTest() {
    scheduleController.deleteSchedule(anyLong());
    Mockito.verify(iScheduleData, times(1)).deleteSchedule(anyLong());
  }

  @Test
  public void getScheduleByIdTest() {
    scheduleController.getScheduleById(anyLong());
    Mockito.verify(iScheduleData, times(1)).getScheduleById(anyLong());
  }

  @Test
  public void SchedulesbyVCandVPTest() {
    scheduleController.SchedulesbyVCandVP(anyLong(), anyLong());
    Mockito.verify(iScheduleData, times(1)).SchedulesbyVCandVP(anyLong(), anyLong());
  }

  @Test
  public void getSchedulesByPlanTest() {
    scheduleController.getSchedulesByPlan(anyLong());
    Mockito.verify(iScheduleData, times(1)).getSchedulesByPlan(anyLong());
  }

  @Test
  public void getNextSchedulesTest() {
    scheduleController.getNextSchedules();
    Mockito.verify(iScheduleData, times(1)).getNextSchedules();
  }

  @Test
  public void unnasignScheduleTest() throws ParseException {
    scheduleController.unnasignSchedule(anyLong());
    Mockito.verify(iScheduleData, times(1)).unnasignSchedule(anyLong());
  }
}
