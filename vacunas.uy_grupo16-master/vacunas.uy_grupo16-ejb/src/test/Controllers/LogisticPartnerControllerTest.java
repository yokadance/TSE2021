package Controllers;

import Controller.LogisticPartnerController;
import IDAL.ILogisticPartnerData;
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
public class LogisticPartnerControllerTest {

  @Spy
  private ILogisticPartnerData iLogisticPartnerData;

  private LogisticPartnerController logisticPartnerController;

  @Before
  public void setup() {
    this.iLogisticPartnerData = mock(ILogisticPartnerData.class);
    this.logisticPartnerController = new LogisticPartnerController();
    this.logisticPartnerController.setiLogisticPartnerData(iLogisticPartnerData);
  }

  @After
  public void teardown() {
    Mockito.reset(iLogisticPartnerData);
  }

  @Test
  public void saveLogisticPartnerTest() {
    logisticPartnerController.saveLogisticPartner(any());
    Mockito.verify(iLogisticPartnerData, times(1)).saveLogisticPartner(any());
  }

  @Test
  public void getLogisticPartnerByIdTest() {
    logisticPartnerController.getLogisticPartnerById(anyLong());
    Mockito.verify(iLogisticPartnerData, times(1)).getLogisticPartnerById(anyLong());
  }

  @Test
  public void deleteLogisticPartnerTest() {
    logisticPartnerController.deleteLogisticPartner(anyLong());
    Mockito.verify(iLogisticPartnerData, times(1)).deleteLogisticPartner(anyLong());
  }

  @Test
  public void listLogisticPartnersTest() {
    logisticPartnerController.listLogisticPartners();
    Mockito.verify(iLogisticPartnerData, times(1)).listLogisticPartners();
  }

  @Test
  public void getLogisticParterByNameTest() {
    logisticPartnerController.getLogisticParterByName(anyString());
    Mockito.verify(iLogisticPartnerData, times(1)).getLogisticParterByName(anyString());
  }

  @Test
  public void removePackageFromPartnerTest() {
    logisticPartnerController.removePackageFromPartner(anyLong(), anyLong());
    Mockito.verify(iLogisticPartnerData, times(1)).removePackageFromPartner(anyLong(), anyLong());
  }

  @Test
  public void getPackagesFromPartnerTest() {
    logisticPartnerController.getPackagesFromPartner(anyLong());
    Mockito.verify(iLogisticPartnerData, times(1)).getPackagesFromPartner(anyLong());
  }

  @Test
  public void getAvailablePackagesToAdd() {
    logisticPartnerController.getAvailablePackagesToAdd();
    Mockito.verify(iLogisticPartnerData, times(1)).getAvailablePackagesToAdd();
  }
}
