package DAL;

import DTO.DTBatch;
import DTO.DTPackage;
import DTO.DTVaccinationCenter;
import DTO.DTVaccinationPlan;
import entities.Package;
import entities.*;
import enumerations.PackageState;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PackageDataTest {

  private PackageData packageData;

  private Package aPackage;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Batch batch;

  private VaccinationCenter vaccinationCenter;

  private VaccinationPlan vaccinationPlan;

  private Vaccine vaccine;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    batch = mock(Batch.class);
    aPackage = mock(Package.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.packageData = new PackageData();
    this.packageData.setData(data);
    this.packageData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(aPackage);
    Mockito.reset(batch);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(vaccine);
  }

  @Test
  public void savePackageTestDoMerge() {
    DTPackage dtp = new DTPackage();
    dtp.setVaccinationCenter("1");
    dtp.setVaccinationPlan("1");
    DTBatch dtb = new DTBatch();
    when(modelMapper.map(dtp, Package.class)).thenReturn(aPackage);
    when(modelMapper.map(dtb, Batch.class)).thenReturn(batch);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getId()).thenReturn(1L);
    when(aPackage.getCreateDate()).thenReturn(new Date());

    packageData.savePackage(dtp, dtb);
  }

  @Test
  public void savePackageTestDoPersist() {
    DTPackage dtp = new DTPackage();
    dtp.setVaccinationCenter("1");
    dtp.setVaccinationPlan("1");
    DTBatch dtb = new DTBatch();
    when(modelMapper.map(dtp, Package.class)).thenReturn(aPackage);
    when(modelMapper.map(dtb, Batch.class)).thenReturn(batch);
    when(data.find(VaccinationCenter.class, 1L)).thenReturn(vaccinationCenter);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getId()).thenReturn(null);
    when(aPackage.getCreateDate()).thenReturn(new Date());

    packageData.savePackage(dtp, dtb);
  }

  @Test
  public void deletePackageTest() {
    when(data.find(Package.class, 1L)).thenReturn(aPackage);

    packageData.deletePackage(1L);
  }

  @Test
  public void getByIdPackageTest() {
    DTPackage dtp = new DTPackage();
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getDTPackage()).thenReturn(dtp);
    when(aPackage.getBatch()).thenReturn(batch);
    when(batch.getId()).thenReturn(1L);

    packageData.getByIdPackage(1L);
  }

  @Test
  public void getPackagesTest() {
    DTPackage dtp = new DTPackage();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);
    when(aPackage.getDTPackage()).thenReturn(dtp);

    packageData.getPackages();
  }

  @Test
  public void getPackagesAvailableTest() {
    DTPackage dtp = new DTPackage();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);
    when(aPackage.getVaccinationCenter()).thenReturn(null);
    when(aPackage.getVaccinationPlan()).thenReturn(null);
    when(aPackage.getDTPackage()).thenReturn(dtp);

    packageData.getPackagesAvailable();
  }

  @Test
  public void getPackagesAvailableForPlanTest() {
    DTPackage dtp = new DTPackage();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);
    when(data.find(VaccinationPlan.class, 1L)).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getVaccine()).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(1L);
    when(aPackage.getVaccinationCenter()).thenReturn(null);
    when(aPackage.getVaccinationPlan()).thenReturn(null);
    when(aPackage.getPackageState()).thenReturn(PackageState.Pending);
    when(aPackage.getBatch()).thenReturn(batch);
    when(batch.getVaccine()).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(1L);
    when(aPackage.getDTPackage()).thenReturn(dtp);

    packageData.getPackagesAvailableForPlan(1L);
  }

  @Test
  public void getPackagesByPlanAndCenterTest() {
    DTPackage dtp = new DTPackage();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("planId", 1L)).thenReturn(typedQuery);
    when(typedQuery.setParameter("centerId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lp);
    when(aPackage.getDTPackage()).thenReturn(dtp);

    packageData.getPackagesByPlanAndCenter(1L, 1L);
  }

  @Test
  public void addPackagetoVCTestReturnTrue() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(modelMapper.map(dtvc, VaccinationCenter.class)).thenReturn(vaccinationCenter);

    packageData.addPackagetoVC(1L, dtvc);
    Assert.assertEquals(true, packageData.addPackagetoVC(1L, dtvc));
  }

  @Test
  public void addPackagetoVCTestReturnFalse() {
    DTVaccinationCenter dtvc = new DTVaccinationCenter();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.find(Package.class, 1L)).thenReturn(null);

    packageData.addPackagetoVC(1L, dtvc);
    Assert.assertEquals(false, packageData.addPackagetoVC(1L, dtvc));
  }

  @Test
  public void addPackagetoVPTestReturnTrue() {
    DTVaccinationPlan dtvp = new DTVaccinationPlan();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(modelMapper.map(dtvp, VaccinationPlan.class)).thenReturn(vaccinationPlan);

    packageData.addPackagetoVP(1L, dtvp);
    Assert.assertEquals(true, packageData.addPackagetoVP(1L, dtvp));
  }

  @Test
  public void addPackagetoVPTestReturnFalse() {
    DTVaccinationPlan dtvp = new DTVaccinationPlan();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.find(Package.class, 1L)).thenReturn(null);

    packageData.addPackagetoVP(1L, dtvp);
    Assert.assertEquals(false, packageData.addPackagetoVP(1L, dtvp));
  }

  @Test
  public void getPackagesByBatchIdTest() {
    DTPackage dtp = new DTPackage();
    List<Package> lp = new ArrayList<>();
    lp.add(aPackage);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("batchId", 1L)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lp);
    when(aPackage.getDTPackage()).thenReturn(dtp);

    packageData.getPackagesByBatchId(1L);
  }

  @Test
  public void getPendingPackagesTest() {
    //    DTPackage dtp = new DTPackage();
    //    List<Package> lp = new ArrayList<>();
    //    lp.add(aPackage);
    //    when(data.createQuery(anyString())).thenReturn(typedQuery);
    //    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    //    when(typedQuery.getResultList()).thenReturn(lp);
    //    when(aPackage.getId()).thenReturn(1L);
    //    when(aPackage.getPackageState()).thenReturn(PackageState.Pending);
    //
    //    packageData.getPendingPackages(1L);
  }

  @Test
  public void packageToDeliveredTest() {
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getId()).thenReturn(1L);

    packageData.packageToDelivered(1L);
  }

  @Test
  public void packageToInTransitTest() {
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getId()).thenReturn(1L);

    packageData.packageToInTransit(1L);
  }

  @Test
  public void discountPackageQuantityTest() {
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(aPackage.getId()).thenReturn(1L);

    packageData.discountPackageQuantity(1L);
  }
}
