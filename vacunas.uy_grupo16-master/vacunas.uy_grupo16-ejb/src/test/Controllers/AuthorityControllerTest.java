package Controllers;

import Controller.AuthorityController;
import IDAL.IAuthorityData;
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
public class AuthorityControllerTest {

  @Spy
  private IAuthorityData iAuthorityData;

  private AuthorityController authorityController;

  @Before
  public void setup() {
    this.iAuthorityData = mock(IAuthorityData.class);
    this.authorityController = new AuthorityController();
    this.authorityController.setiAuthorityData(iAuthorityData);
  }

  @After
  public void teardown() {
    Mockito.reset(iAuthorityData);
  }

  @Test
  public void saveAuthorityTest() {
    authorityController.saveAuthority(any());
    Mockito.verify(iAuthorityData, times(1)).saveAuthority(any());
  }

  @Test
  public void getByIdAuthorityTest() {
    authorityController.getByIdAuthority(anyLong());
    Mockito.verify(iAuthorityData, times(1)).getByIdAuthority(anyLong());
  }

  @Test
  public void getAuthorityTest() {
    authorityController.getAuthority();
    Mockito.verify(iAuthorityData, times(1)).getAuthority();
  }

  @Test
  public void deleteAuthorityTest() {
    authorityController.deleteAuthority(anyLong());
    Mockito.verify(iAuthorityData, times(1)).deleteAuthority(anyLong());
  }
}
