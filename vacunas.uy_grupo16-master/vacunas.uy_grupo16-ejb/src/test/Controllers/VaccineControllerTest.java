package Controllers;

import Controller.VaccineController;
import IDAL.IVaccineData;
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
public class VaccineControllerTest {

  @Spy
  IVaccineData iVaccineData;

  VaccineController vaccineController;

  @Before
  public void setup() {
    this.iVaccineData = mock(IVaccineData.class);
    this.vaccineController = new VaccineController();
    this.vaccineController.setiVaccineData(iVaccineData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccineData);
  }

  @Test
  public void listVaccinesTest() {
    vaccineController.listVaccines();
    Mockito.verify(iVaccineData, times(1)).listVaccines();
  }

  @Test
  public void saveVaccineTest() {
    vaccineController.saveVaccine(any());
    Mockito.verify(iVaccineData, times(1)).saveVaccine(any());
  }

  @Test
  public void deleteVaccineTest() {
    vaccineController.deleteVaccine(anyLong());
    Mockito.verify(iVaccineData, times(1)).deleteVaccine(anyLong());
  }

  @Test
  public void getVaccineByIdTest() {
    vaccineController.getVaccineById(anyLong());
    Mockito.verify(iVaccineData, times(1)).getVaccineById(anyLong());
  }

  @Test
  public void addDiseaseToVaccineTest() {
    vaccineController.addDiseaseToVaccine(anyLong(), any());
    Mockito.verify(iVaccineData, times(1)).addDiseaseToVaccine(anyLong(), any());
  }

  @Test
  public void addLaboratoryToVaccineTest() {
    vaccineController.addLaboratoryToVaccine(anyLong(), any());
    Mockito.verify(iVaccineData, times(1)).addLaboratoryToVaccine(anyLong(), any());
  }

  @Test
  public void getVaccineReportTest() {
    vaccineController.getVaccineReport(anyLong());
    Mockito.verify(iVaccineData, times(1)).getVaccineReport(anyLong());
  }
}
