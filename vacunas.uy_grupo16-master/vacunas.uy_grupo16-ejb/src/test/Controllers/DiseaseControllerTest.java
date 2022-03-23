package Controllers;

import Controller.DiseaseController;
import IDAL.IDiseaseData;
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
public class DiseaseControllerTest {

  @Spy
  private IDiseaseData iDiseaseData;

  private DiseaseController diseaseController;

  @Before
  public void setup() {
    this.iDiseaseData = mock(IDiseaseData.class);
    this.diseaseController = new DiseaseController();
    this.diseaseController.setiDiseaseData(iDiseaseData);
  }

  @After
  public void teardown() {
    Mockito.reset(iDiseaseData);
  }

  @Test
  public void listDiseasesTest() {
    diseaseController.listDiseases();
    Mockito.verify(iDiseaseData, times(1)).listDiseases();
  }

  @Test
  public void deleteDiseaseTest() {
    diseaseController.deleteDisease(anyLong());
    Mockito.verify(iDiseaseData, times(1)).deleteDisease(anyLong());
  }

  @Test
  public void saveDiseaseTest() {
    diseaseController.saveDisease(any());
    Mockito.verify(iDiseaseData, times(1)).saveDisease(any());
  }

  @Test
  public void getDiseaseByIdTest() {
    diseaseController.getDiseaseById(anyLong());
    Mockito.verify(iDiseaseData, times(1)).getDiseaseById(anyLong());
  }

  @Test
  public void getDiseaseByNameTest() throws Exception {
    diseaseController.getDiseaseByName(anyString());
    Mockito.verify(iDiseaseData, times(1)).getDiseaseByName(anyString());
  }

  @Test
  public void getDiseaseIdByNameTest() {
    diseaseController.getDiseaseIdByName(anyString());
    Mockito.verify(iDiseaseData, times(1)).getDiseaseIdByName(anyString());
  }

  @Test
  public void addVaccineToDiseaseTest() {
    diseaseController.addVaccineToDisease(anyLong(), any());
    Mockito.verify(iDiseaseData, times(1)).addVaccineToDisease(anyLong(), any());
  }

  @Test
  public void checkDiseaseNameAvailabilityTest() {
    diseaseController.checkDiseaseNameAvailability(anyString());
    Mockito.verify(iDiseaseData, times(1)).checkDiseaseNameAvailability(anyString());
  }
}
