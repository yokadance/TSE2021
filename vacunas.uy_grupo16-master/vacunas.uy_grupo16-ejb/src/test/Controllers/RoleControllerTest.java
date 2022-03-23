package Controllers;

import Controller.RoleController;
import IDAL.IRoleData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

  @Spy
  private IRoleData iroledata;

  private RoleController roleController;

  @Before
  public void setup() {
    this.iroledata = mock(IRoleData.class);
    this.roleController = new RoleController();
    this.roleController.setIroledata(iroledata);
  }

  @After
  public void teardown() {
    Mockito.reset(iroledata);
  }

  @Test
  public void getRolesTest() {
    roleController.getRoles();
    Mockito.verify(iroledata, times(1)).getRoles();
  }

  @Test
  public void saveRoleTest() {
    roleController.saveRole(any());
    Mockito.verify(iroledata, times(1)).saveRole(any());
  }

  @Test
  public void deleteRoleTest() {
    roleController.deleteRole(any());
    Mockito.verify(iroledata, times(1)).deleteRole(any());
  }

  @Test
  public void saveAnyRoleTest() {
    roleController.saveAnyRole(any());
    Mockito.verify(iroledata, times(1)).saveAnyRole(any());
  }
}
