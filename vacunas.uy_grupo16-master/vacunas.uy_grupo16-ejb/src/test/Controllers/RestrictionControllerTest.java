package Controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import Controller.RestrictionController;
import IDAL.IRestrictionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestrictionControllerTest {

  @Spy
  private IRestrictionData iRestrictionData;

  private RestrictionController restrictionController;

  @Before
  public void setup() {
    this.iRestrictionData = mock(IRestrictionData.class);
    this.restrictionController = new RestrictionController();
    this.restrictionController.setiRestrictionData(iRestrictionData);
  }

  @After
  public void teardown() {
    Mockito.reset(iRestrictionData);
  }

  @Test
  public void saveRestrictionTest() {
    restrictionController.saveRestriction(any());
    Mockito.verify(iRestrictionData, times(1)).saveRestriction(any());
  }

  @Test
  public void getByIdRestrictionTest() {
    restrictionController.getByIdRestriction(anyLong());
    Mockito.verify(iRestrictionData, times(1)).getRestrictionById(anyLong());
  }

  @Test
  public void getRestrictionsTest() {
    restrictionController.getRestrictions();
    Mockito.verify(iRestrictionData, times(1)).listRestrictions();
  }

  @Test
  public void deleteRestrictionTest() {
    restrictionController.deleteRestriction(anyLong());
    Mockito.verify(iRestrictionData, times(1)).deleteRestriction(anyLong());
  }

  @Test
  public void getRestrictionsByPlanTest() {
    restrictionController.getRestrictionsByPlan(anyLong());
    Mockito.verify(iRestrictionData, times(1)).getRestrictionsByPlan(anyLong());
  }

  @Test
  public void getRestrictionByDataTest() {
    restrictionController.getRestrictionByData(anyLong());
    Mockito.verify(iRestrictionData, times(1)).getRestrictionByData(anyLong());
  }

  @Test
  public void getRestrictionsByVaccinationPlanTest() {
    restrictionController.getRestrictionsByVaccinationPlan(anyLong());
    Mockito.verify(iRestrictionData, times(1)).getRestrictionsByVaccinationPlan(anyLong());
  }

  @Test
  public void callAgeRestrictionApiTest() {
    restrictionController.callAgeRestrictionApi(anyInt(), anyLong());
    Mockito.verify(iRestrictionData, times(1)).callAgeRestrictionApi(anyInt(), anyLong());
  }
}
