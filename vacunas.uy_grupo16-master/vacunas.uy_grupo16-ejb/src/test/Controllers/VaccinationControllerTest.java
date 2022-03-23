package Controllers;

import Controller.VaccinationController;
import IDAL.IVaccinationData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationControllerTest {

  @Spy
  private IVaccinationData iVaccinationData;

  private VaccinationController vaccinationController;

  @Before
  public void setup() {
    this.iVaccinationData = mock(IVaccinationData.class);
    this.vaccinationController = new VaccinationController();
    this.vaccinationController.setiVaccinationData(iVaccinationData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationData);
  }

  @Test
  public void newVaccinationTest() {
    vaccinationController.newVaccination(any());
    Mockito.verify(iVaccinationData, times(1)).saveVaccination(any());
  }

  @Test
  public void listVaccinationTest() {
    vaccinationController.listVaccination();
    Mockito.verify(iVaccinationData, times(1)).listVaccinations();
  }

  @Test
  public void saveVaccinationTest() {
    vaccinationController.saveVaccination(any());
    Mockito.verify(iVaccinationData, times(1)).saveVaccination(any());
  }

  @Test
  public void deleteVaccinationTest() {
    vaccinationController.deleteVaccination(anyLong());
    Mockito.verify(iVaccinationData, times(1)).deleteVaccination(anyLong());
  }

  @Test
  public void getVaccinationByIdTest() {
    vaccinationController.getVaccinationById(anyLong());
    Mockito.verify(iVaccinationData, times(1)).getVaccinationById(anyLong());
  }
}
