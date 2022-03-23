package Controllers;

import Controller.VaccinationCenterController;
import IDAL.IVaccinationCenterData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationCenterControllerTest {

  @Spy
  private IVaccinationCenterData iVaccinationCenterData;

  private VaccinationCenterController vaccinationCenterController;

  @Before
  public void setup() {
    this.iVaccinationCenterData = mock(IVaccinationCenterData.class);
    this.vaccinationCenterController = new VaccinationCenterController();
    this.vaccinationCenterController.setiVaccinationCenterData(iVaccinationCenterData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationCenterData);
  }

  @Test
  public void newVaccinationCenterTest() {
    vaccinationCenterController.newVaccinationCenter(any());
    Mockito.verify(iVaccinationCenterData, times(1)).newVaccinationCenter(any());
  }

  @Test
  public void listVaccinationCentersTest() {
    vaccinationCenterController.listVaccinationCenters();
    Mockito.verify(iVaccinationCenterData, times(1)).listVaccinationsCenters();
  }

  @Test
  public void saveVaccinationCenterTest() {
    vaccinationCenterController.saveVaccinationCenter(any());
    Mockito.verify(iVaccinationCenterData, times(1)).saveVaccinationCenter(any());
  }

  @Test
  public void deleteVaccinationCenterTest() {
    vaccinationCenterController.deleteVaccinationCenter(anyLong());
    Mockito.verify(iVaccinationCenterData, times(1)).deleteVaccinationCenter(anyLong());
  }

  @Test
  public void getVaccinationCenterByIdTest() {
    vaccinationCenterController.getVaccinationCenterById(anyLong());
    Mockito.verify(iVaccinationCenterData, times(1)).getVaccinationCenterById(anyLong());
  }

  @Test
  public void getCentersByPlanTest() {
    vaccinationCenterController.getCentersByPlan(anyLong());
    Mockito.verify(iVaccinationCenterData, times(1)).getCentersByPlan(anyLong());
  }

  @Test
  public void vaccinationCenterPasswordTest() {
    vaccinationCenterController.vaccinationCenterPassword();
    Mockito.verify(iVaccinationCenterData, times(1)).vaccinationCenterPassword();
  }

  @Test
  public void validatePasswordTest() {
    vaccinationCenterController.validatePassword(anyLong(), anyString());
    Mockito.verify(iVaccinationCenterData, times(1)).validatePassword(anyLong(), anyString());
  }

  @Test
  public void getCentersAvailablesByPlanTest() {
    vaccinationCenterController.getCentersAvailablesByPlan(anyLong());
    Mockito.verify(iVaccinationCenterData, times(1)).getCentersAvailablesByPlan(anyLong());
  }
}
