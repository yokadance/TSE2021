package Controllers;

import Controller.LaboratoryController;
import DTO.DTLaboratory;
import IDAL.ILaboratoryData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LaboratoryControllerTest {

  @Spy
  ILaboratoryData iLaboratoryData;

  private LaboratoryController laboratoryController;

  @Before
  public void setup() {
    this.iLaboratoryData = mock(ILaboratoryData.class);
    this.laboratoryController = new LaboratoryController();
    this.laboratoryController.setiLaboratoryData(iLaboratoryData);
  }

  @After
  public void teardown() {
    Mockito.reset(iLaboratoryData);
  }

  @Test
  public void saveLaboratoryTest() {
    laboratoryController.saveLaboratory(any());
    Mockito.verify(iLaboratoryData, times(1)).saveLaboratory(any());
  }

  @Test
  public void newLaboratoryTest() {
    //Prueba de Test DTLaboratory
    String fakeString = "Test";
    DTLaboratory dtl = new DTLaboratory(fakeString, fakeString, fakeString, fakeString);
    dtl = new DTLaboratory();
    dtl.setName("Test");
    dtl.setUserid("Test");
    dtl.setOrigin("Test");
    dtl.setEmail("Test");
    dtl.setPhone("Test");
    dtl.getName();
    dtl.getOrigin();
    dtl.getEmail();
    dtl.getPhone();
    dtl.getDTVaccines();
    //
    dtl.setDTVaccines(new ArrayList<>());
    laboratoryController.newLaboratory("Test", "Test", "Test", "Test", "Test", new ArrayList<>());
    Mockito.verify(iLaboratoryData, times(1)).saveLaboratory(any());
  }

  @Test
  public void getLaboratoryByIdTest() {
    laboratoryController.getLaboratoryById(anyLong());
    Mockito.verify(iLaboratoryData, times(1)).getLaboratoryById(anyLong());
  }

  @Test
  public void updateLaboratoryTest() {
    laboratoryController.updateLaboratory(any());
    Mockito.verify(iLaboratoryData, times(1)).saveLaboratory(any());
  }

  @Test
  public void listLaboratoriesTest() {
    laboratoryController.listLaboratories();
    Mockito.verify(iLaboratoryData, times(1)).listLaboratorys();
  }

  @Test
  public void deleteLaboratoryTest() {
    laboratoryController.deleteLaboratory(anyLong());
    Mockito.verify(iLaboratoryData, times(1)).deleteLaboratory(anyLong());
  }

  @Test
  public void getLaboratoryByNameTest() {
    laboratoryController.getLaboratoryByName(anyString());
    Mockito.verify(iLaboratoryData, times(1)).getLaboratoryByName(anyString());
  }

  @Test
  public void getLaboratoryIdByNameTest() {
    laboratoryController.getLaboratoryIdByName(anyString());
    Mockito.verify(iLaboratoryData, times(1)).getLaboratoryIdByName(anyString());
  }

  @Test
  public void checkLaboratoryNameAvailabilityTest() {
    laboratoryController.checkLaboratoryNameAvailability(anyString());
    Mockito.verify(iLaboratoryData, times(1)).checkLaboratoryNameAvailability(anyString());
  }
}
