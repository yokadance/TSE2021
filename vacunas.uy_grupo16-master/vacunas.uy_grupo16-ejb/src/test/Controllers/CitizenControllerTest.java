package Controllers;

import Controller.CitizenController;
import IDAL.ICitizenData;
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
public class CitizenControllerTest {

  @Spy
  private ICitizenData iCitizenData;

  private CitizenController citizenController;

  @Before
  public void setup() {
    this.iCitizenData = mock(ICitizenData.class);
    this.citizenController = new CitizenController();
    this.citizenController.setiCitizenData(iCitizenData);
  }

  @After
  public void teardown() {
    Mockito.reset(iCitizenData);
  }

  @Test
  public void saveCitizenTest() {
    citizenController.saveCitizen(any());
    Mockito.verify(iCitizenData, times(1)).saveCitizen(any());
  }

  @Test
  public void getByIdCitizenTest() {
    citizenController.getByIdCitizen(anyLong());
    Mockito.verify(iCitizenData, times(1)).getByIdCitizen(any());
  }

  @Test
  public void getCitizensTest() {
    citizenController.getCitizens();
    Mockito.verify(iCitizenData, times(1)).getCitizens();
  }

  @Test
  public void deleteCitizenTest() {
    citizenController.deleteCitizen(anyLong());
    Mockito.verify(iCitizenData, times(1)).deleteCitizen(any());
  }

  @Test
  public void getCitizenIdByCiTest() {
    citizenController.getCitizenIdByCi(anyString());
    Mockito.verify(iCitizenData, times(1)).getCitizenIdByCi(any());
  }

  @Test
  public void getCitizenByCiTest() {
    citizenController.getCitizenByCi(anyString());
    Mockito.verify(iCitizenData, times(1)).getCitizenByCi(any());
  }

  @Test
  public void setTokenTest() {
    citizenController.setToken(anyString(), anyString());
    Mockito.verify(iCitizenData, times(1)).setToken(anyString(), anyString());
  }
}
