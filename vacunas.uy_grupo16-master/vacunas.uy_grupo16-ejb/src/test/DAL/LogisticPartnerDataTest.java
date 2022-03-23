package DAL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTLogisticPartner;
import DTO.DTPackage;
import IController.IPackageController;
import entities.LogisticPartner;
import entities.Package;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class LogisticPartnerDataTest {

  private LogisticPartnerData logisticPartnerData;

  private LogisticPartner logisticPartner;

  private IPackageController iPackageController;

  private Package aPackage;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  @Before
  public void setup() {
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    logisticPartner = mock(LogisticPartner.class);
    aPackage = mock(Package.class);
    iPackageController = mock(IPackageController.class);
    this.logisticPartnerData = new LogisticPartnerData();
    this.logisticPartnerData.setData(data);
    this.logisticPartnerData.setModelMapper(modelMapper);
    this.logisticPartnerData.setiPackageController(iPackageController);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(logisticPartner);
    Mockito.reset(aPackage);
    Mockito.reset(iPackageController);
  }

  @Test
  public void saveLogisticPartnerTestWithId() {
    DTLogisticPartner dtlp = new DTLogisticPartner();
    when(modelMapper.map(dtlp, LogisticPartner.class)).thenReturn(logisticPartner);
    when(logisticPartner.getId()).thenReturn(1L);
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);
    when(logisticPartner.getCreateDate()).thenReturn(new Date());

    logisticPartnerData.saveLogisticPartner(dtlp);
  }

  @Test
  public void saveLogisticPartnerTestWithoutId() {
    DTLogisticPartner dtlp = new DTLogisticPartner();
    when(modelMapper.map(dtlp, LogisticPartner.class)).thenReturn(logisticPartner);
    when(logisticPartner.getId()).thenReturn(null);

    logisticPartnerData.saveLogisticPartner(dtlp);
  }

  @Test
  public void getLogisticPartnerByIdTest() {
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);
    when(logisticPartner.getDTLogisticPartner()).thenReturn(new DTLogisticPartner());

    logisticPartnerData.getLogisticPartnerById(1L);
  }

  @Test
  public void deleteLogisticPartnerTest() {
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);

    logisticPartnerData.deleteLogisticPartner(1L);
  }

  @Test
  public void listLogisticPartnersTest() {
    List<LogisticPartner> lLp = new ArrayList<>();
    lLp.add(logisticPartner);
    when(data.createQuery("select l from LogisticPartner l where l.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lLp);
    when(logisticPartner.getDTLogisticPartner()).thenReturn(new DTLogisticPartner());

    logisticPartnerData.listLogisticPartners();
  }

  @Test
  public void getLogisticParterByNameTest() {
    List<LogisticPartner> lLp = new ArrayList<>();
    lLp.add(logisticPartner);
    when(data.createQuery("select l from LogisticPartner l where l.deleteDate is null and LOWER(l.name) like LOWER(:name)"))
      .thenReturn(typedQuery);
    when(typedQuery.setParameter("name", "%Test%")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lLp);
    when(logisticPartner.getDTLogisticPartner()).thenReturn(new DTLogisticPartner());

    logisticPartnerData.getLogisticParterByName("Test");
  }

  @Test
  public void removePackageFromPartnerTest() {
    DTLogisticPartner dtlp = new DTLogisticPartner();
    List<Package> lP = new ArrayList<>();
    lP.add(aPackage);
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);
    when(logisticPartner.getDTLogisticPartner()).thenReturn(dtlp);
    when(logisticPartner.getPackages()).thenReturn(lP);
    when(aPackage.getId()).thenReturn(2L);
    when(aPackage.getDTPackage()).thenReturn(new DTPackage());

    //Save logistic partner
    when(modelMapper.map(dtlp, LogisticPartner.class)).thenReturn(logisticPartner);
    when(logisticPartner.getId()).thenReturn(1L);
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);
    when(logisticPartner.getCreateDate()).thenReturn(new Date());

    logisticPartnerData.removePackageFromPartner(1L, 1L);
  }

  @Test
  public void getPackagesFromPartnerTest() {
    List<Package> lP = new ArrayList<>();
    lP.add(aPackage);
    when(data.find(LogisticPartner.class, 1L)).thenReturn(logisticPartner);
    when(logisticPartner.getPackages()).thenReturn(lP);
    when(aPackage.getDTPackage()).thenReturn(new DTPackage());

    logisticPartnerData.getPackagesFromPartner(1L);
  }

  @Test
  public void getAvailablePackagesToAddTest() {
    List<DTPackage> lDtp = new ArrayList<>();
    DTPackage dtp = new DTPackage();
    dtp.setId(1L);
    lDtp.add(dtp);
    List<Package> lP = new ArrayList<>();
    lP.add(aPackage);
    List<LogisticPartner> lLp = new ArrayList<>();
    lLp.add(logisticPartner);
    when(data.createQuery("select l from LogisticPartner l where l.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lLp);
    when(iPackageController.getPackages()).thenReturn(lDtp);
    when(logisticPartner.getPackages()).thenReturn(lP);
    when(aPackage.getId()).thenReturn(1L);

    logisticPartnerData.getAvailablePackagesToAdd();
  }
}
