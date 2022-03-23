package Controllers;

import Controller.VaccinationPostController;
import IDAL.IVaccinationPostData;
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
public class VaccinationPostControllerTest {

  @Spy
  private IVaccinationPostData iVaccinationPostData;

  private VaccinationPostController vaccinationPostController;

  @Before
  public void setup() {
    this.iVaccinationPostData = mock(IVaccinationPostData.class);
    this.vaccinationPostController = new VaccinationPostController();
    this.vaccinationPostController.setiVaccinationPostData(iVaccinationPostData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationPostData);
  }

  @Test
  public void saveVaccinationPostTest() {
    vaccinationPostController.saveVaccinationPost(any());
    Mockito.verify(iVaccinationPostData, times(1)).saveVaccinationPost(any());
  }

  @Test
  public void getByIdVaccinationPostTest() {
    vaccinationPostController.getByIdVaccinationPost(anyLong());
    Mockito.verify(iVaccinationPostData, times(1)).getByIdVaccinationPost(any());
  }

  @Test
  public void getVaccinationPostsTest() {
    vaccinationPostController.getVaccinationPosts();
    Mockito.verify(iVaccinationPostData, times(1)).getVaccinationPosts();
  }

  @Test
  public void deleteVaccinationPostTest() {
    vaccinationPostController.deleteVaccinationPost(anyLong());
    Mockito.verify(iVaccinationPostData, times(1)).deleteVaccinationPost(any());
  }

  @Test
  public void getByVaccinatorCenterTest() {
    vaccinationPostController.getByVaccinatorCenter(anyLong());
    Mockito.verify(iVaccinationPostData, times(1)).getByVaccinatorCenter(any());
  }

  @Test
  public void createVaccinationPostTest() {
    vaccinationPostController.createVaccinationPost(any());
    Mockito.verify(iVaccinationPostData, times(1)).createVaccinationPost(any());
  }
}
