package Controllers;

import Controller.VaccinationActController;
import IController.ICitizenController;
import IController.IPackageController;
import IController.IVaccinationPostController;
import IDAL.IVaccinationActData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ejb.EJB;
import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class VaccinationActControllerTest {

  @EJB
  private IVaccinationActData iVaccinationActData;

  @Inject
  private ICitizenController iCitizenController;

  @Inject
  private IPackageController iPackageController;

  @Inject
  private IVaccinationPostController iVaccinationPostController;

  private VaccinationActController vaccinationActController;

  @Before
  public void setup() {
    this.iVaccinationActData = mock(IVaccinationActData.class);
    this.iCitizenController = mock(ICitizenController.class);
    this.iPackageController = mock(IPackageController.class);
    this.iVaccinationPostController = mock(IVaccinationPostController.class);
    this.vaccinationActController = new VaccinationActController();
    this.vaccinationActController.setiVaccinationPostController(iVaccinationPostController);
    this.vaccinationActController.setiVaccinationActData(iVaccinationActData);
    this.vaccinationActController.setiCitizenController(iCitizenController);
    this.vaccinationActController.setiPackageController(iPackageController);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationActData);
    Mockito.reset(iCitizenController);
    Mockito.reset(iPackageController);
    Mockito.reset(iVaccinationPostController);
  }

  @Test
  public void listVaccinationActsTest() {
    vaccinationActController.listVaccinationActs();
    Mockito.verify(iVaccinationActData, times(1)).listVaccinationAct();
  }

  @Test
  public void saveVaccinationActTest() {
    vaccinationActController.saveVaccinationAct(any());
    Mockito.verify(iVaccinationActData, times(1)).saveVaccinationAct(any());
  }

  @Test
  public void deleteVaccinationActTest() {
    vaccinationActController.deleteVaccinationAct(anyLong());
    Mockito.verify(iVaccinationActData, times(1)).deleteVaccinationAct(anyLong());
  }

  @Test
  public void getVaccinationActByIdTest() {
    vaccinationActController.getVaccinationActById(anyLong());
    Mockito.verify(iVaccinationActData, times(1)).getVaccinationActById(anyLong());
  }

  @Test
  public void vaccinationActByCiTest() {
    vaccinationActController.vaccinationActByCi(anyString());
    Mockito.verify(iVaccinationActData, times(1)).vaccinationActByCi(anyString());
  }

  @Test
  public void getVaccinationActsViewTest() {
    vaccinationActController.getVaccinationActsView();
    Mockito.verify(iVaccinationActData, times(1)).getVaccinationActsView();
  }
}
