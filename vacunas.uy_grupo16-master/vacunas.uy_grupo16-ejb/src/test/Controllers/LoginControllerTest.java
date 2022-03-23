package Controllers;

import Controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

  private LoginController loginController;

  @Before
  public void setup() {
    this.loginController = new LoginController();
  }

  @Test
  public void loginGubUyTest() {
    loginController.loginGubUy("Test", "Test");
  }
}
