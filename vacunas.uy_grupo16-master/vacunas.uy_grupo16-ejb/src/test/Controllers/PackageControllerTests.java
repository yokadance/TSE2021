package Controllers;

import Controller.PackageController;
import DTO.DTBatch;
import DTO.DTPackage;
import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import IController.IBatchController;
import IController.IVaccinationCenterController;
import IController.IVaccinationPlanController;
import IDAL.IPackageData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PackageControllerTests {

  @Spy
  IVaccinationCenterController iVaccinationCenterController;

  @Spy
  IVaccinationPlanController iVaccinationPlanController;

  @Spy
  IBatchController iBatchController;

  @Spy
  IPackageData iPackageData;

  PackageController packageController;

  @Before
  public void setup() {
    this.iVaccinationCenterController = mock(IVaccinationCenterController.class);
    this.iVaccinationPlanController = mock(IVaccinationPlanController.class);
    this.iBatchController = mock(IBatchController.class);
    this.iPackageData = mock(IPackageData.class);
    this.packageController = new PackageController();
    this.packageController.setiVaccinationCenterController(iVaccinationCenterController);
    this.packageController.setiVaccinationPlanController(iVaccinationPlanController);
    this.packageController.setiBatchController(iBatchController);
    this.packageController.setiPackageData(iPackageData);
  }

  @After
  public void teardown() {
    Mockito.reset(iVaccinationCenterController);
    Mockito.reset(iVaccinationPlanController);
    Mockito.reset(iBatchController);
    Mockito.reset(iPackageData);
  }

  @Test
  public void savePackageTest() {
    DTPackage dtp = new DTPackage();
    dtp.setBatch("1");
    DTBatch dtb = new DTBatch();
    packageController.savePackage(dtp);
    when(iBatchController.getByIdBatch(anyLong())).thenReturn(dtb);

    Mockito.verify(iPackageData, times(1)).savePackage(any(), any());
  }

  @Test
  public void getByIdPackageTest() {
    packageController.getByIdPackage(anyLong());
    Mockito.verify(iPackageData, times(1)).getByIdPackage(anyLong());
  }

  @Test
  public void getPackages() {
    packageController.getPackages();
    Mockito.verify(iPackageData, times(1)).getPackages();
  }

  @Test
  public void deletePackageTest() {
    packageController.deletePackage(anyLong());
    Mockito.verify(iPackageData, times(1)).deletePackage(anyLong());
  }

  @Test
  public void addPackagetoVCTest() {
    DTPackage dtp = new DTPackage();
    dtp.setBatch("1");
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    packageController.addPackagetoVC(1L, 1L);
    when(iVaccinationCenterController.getVaccinationCenterById(1L)).thenReturn(dtvc);

    Mockito.verify(iPackageData, times(1)).addPackagetoVC(any(), any());
  }

  @Test
  public void addPackagetoVPTest() {
    DTPackage dtp = new DTPackage();
    dtp.setBatch("1");
    DTVaccinationPlan dtvp = new DTVaccinationPlan();
    packageController.addPackagetoVP(1L, 1L);
    when(iVaccinationPlanController.getVaccinationPlanById(1L)).thenReturn(dtvp);

    Mockito.verify(iPackageData, times(1)).addPackagetoVP(any(), any());
  }

  @Test
  public void getPackagesByBatchIdTest() {
    packageController.getPackagesByBatchId(anyLong());
    Mockito.verify(iPackageData, times(1)).getPackagesByBatchId(anyLong());
  }


  @Test
  public void getPendingPackagesTest() {
    packageController.getPendingPackages(1L);
    Mockito.verify(iPackageData, times(1)).getPendingPackages(1L);
  }

  @Test
  public void packageToDeliveredTest() {
    packageController.packageToDelivered(anyLong());
    Mockito.verify(iPackageData, times(1)).packageToDelivered(anyLong());
  }

  @Test
  public void packageToInTransitTest() {
    packageController.packageToInTransit(anyLong());
    Mockito.verify(iPackageData, times(1)).packageToInTransit(anyLong());
  }

  @Test
  public void discountPackageQuantityTest() {
    packageController.discountPackageQuantity(anyLong());
    Mockito.verify(iPackageData, times(1)).discountPackageQuantity(anyLong());
  }

  @Test
  public void getPackagesAvailableTest() {
    packageController.getPackagesAvailable();
    Mockito.verify(iPackageData, times(1)).getPackagesAvailable();
  }

  @Test
  public void getPackagesAvailableForPlanTest() {
    packageController.getPackagesAvailableForPlan(anyLong());
    Mockito.verify(iPackageData, times(1)).getPackagesAvailableForPlan(anyLong());
  }

  @Test
  public void getPackagesByPlanAndCenterTest() {
    packageController.getPackagesByPlanAndCenter(anyLong(), anyLong());
    Mockito.verify(iPackageData, times(1)).getPackagesByPlanAndCenter(anyLong(), anyLong());
  }

  @Test
  public void unassignPackageFromCenterAndPlanTest() {
    packageController.unassignPackageFromCenterAndPlan(anyLong());
    Mockito.verify(iPackageData, times(1)).unassignPackageFromCenterAndPlan(anyLong());
  }
}
