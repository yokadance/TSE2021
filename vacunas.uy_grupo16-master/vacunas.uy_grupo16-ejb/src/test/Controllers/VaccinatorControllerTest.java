package Controllers;

import Controller.VaccinatorController;
import DTO.DTVaccinationPost;
import IController.IVaccinationPostController;
import IDAL.IVaccinatorData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VaccinatorControllerTest {

  @Spy
  private IVaccinatorData iVaccinatorData;

  @Spy
  private IVaccinationPostController iVaccinationPostController;

  private VaccinatorController vaccinatorController;

  @Before
  public void setup() {
    this.iVaccinatorData = mock(IVaccinatorData.class);
    this.iVaccinationPostController = mock(IVaccinationPostController.class);
    this.vaccinatorController = new VaccinatorController();
    this.vaccinatorController.setiVaccinationPostController(iVaccinationPostController);
    this.vaccinatorController.setiVaccinatorData(iVaccinatorData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinatorData);
    Mockito.reset(iVaccinationPostController);
  }

  @Test
  public void saveVaccinatorTest() {
    vaccinatorController.saveVaccinator(any());
    Mockito.verify(iVaccinatorData, times(1)).saveVaccinator(any());
  }

  @Test
  public void getByIdVaccinatorTest() {
    vaccinatorController.getByIdVaccinator(anyLong());
    Mockito.verify(iVaccinatorData, times(1)).getByIdVaccinator(any());
  }

  @Test
  public void getVaccinatorsTest() {
    vaccinatorController.getVaccinators();
    Mockito.verify(iVaccinatorData, times(1)).getVaccinators();
  }

  @Test
  public void deleteVaccinatorTest() {
    vaccinatorController.deleteVaccinator(anyLong());
    Mockito.verify(iVaccinatorData, times(1)).deleteVaccinator(any());
  }

  @Test
  public void setVCtoVTest() {
    DTVaccinationPost dtvp = new DTVaccinationPost();
    when(iVaccinationPostController.getByIdVaccinationPost(1L)).thenReturn(dtvp);
    vaccinatorController.setVCtoV(1L, 1L);
    Mockito.verify(iVaccinatorData, times(1)).setVCtoV(1L, dtvp);
  }

  @Test
  public void getVaccinatorDatabyCiTest() {
    vaccinatorController.getVaccinatorDatabyCi(anyString());
    Mockito.verify(iVaccinatorData, times(1)).getVaccinatorDatabyCi(any());
  }

  @Test
  public void getVaccinatorByCiTest() {
    vaccinatorController.getVaccinatorByCi(anyString());
    Mockito.verify(iVaccinatorData, times(1)).getVaccinatorByCi(any());
  }
}
