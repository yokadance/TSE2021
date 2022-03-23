package Controllers;

import Controller.AdministratorController;
import IDAL.IAdministratorData;
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
public class AdministratorControllerTest {

  @Spy
  private IAdministratorData iAdministratorData;

  private AdministratorController administratorController;

  @Before
  public void setup() {
    this.iAdministratorData = mock(IAdministratorData.class);
    this.administratorController = new AdministratorController();
    this.administratorController.setiAdministratorData(iAdministratorData);
  }

  @After
  public void teardown() {
    Mockito.reset(iAdministratorData);
  }

  @Test
  public void saveAdministratorTest() {
    administratorController.saveAdministrator(any());
    Mockito.verify(iAdministratorData, times(1)).saveAdministrator(any());
  }

  @Test
  public void getByIdAdministratorTest() {
    administratorController.getByIdAdministrator(anyLong());
    Mockito.verify(iAdministratorData, times(1)).getByIdAdministrator(anyLong());
  }

  @Test
  public void getAdministratorsTest() {
    administratorController.getAdministrators();
    Mockito.verify(iAdministratorData, times(1)).getAdministrators();
  }

  @Test
  public void deleteAdministratorTest() {
    administratorController.deleteAdministrator(anyLong());
    Mockito.verify(iAdministratorData, times(1)).deleteAdministrator(anyLong());
  }
}
