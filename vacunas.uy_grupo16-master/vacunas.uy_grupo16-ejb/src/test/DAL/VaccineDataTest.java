package DAL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTDisease;
import DTO.DTLaboratory;
import DTO.DTPackage;
import DTO.DTVaccine;
import IController.IPackageController;
import entities.*;
import entities.Package;
import enumerations.PackageState;
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
public class VaccineDataTest {

  private VaccineData vaccineData;

  private Vaccine vaccine;

  private Batch batch;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Disease disease;

  private Laboratory laboratory;

  private IPackageController iPackageController;

  private Package aPackage;

  private VaccinationPlan vaccinationPlan;

  private VaccinationCenter vaccinationCenter;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    disease = mock(Disease.class);
    laboratory = mock(Laboratory.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    batch = mock(Batch.class);
    iPackageController = mock(IPackageController.class);
    aPackage = mock(Package.class);
    vaccinationPlan = mock(VaccinationPlan.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    this.vaccineData = new VaccineData();
    this.vaccineData.setData(data);
    this.vaccineData.setModelMapper(modelMapper);
    this.vaccineData.setiPackageController(iPackageController);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccine);
    Mockito.reset(disease);
    Mockito.reset(laboratory);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(batch);
    Mockito.reset(iPackageController);
    Mockito.reset(aPackage);
    Mockito.reset(vaccinationPlan);
    Mockito.reset(vaccinationCenter);
  }

  @Test
  public void saveVaccineTestWithVaccineIdNotNull() {
    DTVaccine dtv = new DTVaccine();
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(1L);
    when(vaccine.getCreateDate()).thenReturn(new Date());
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);

    vaccineData.saveVaccine(dtv);
  }

  @Test
  public void saveVaccineTestWithVaccineIdNull() {
    DTVaccine dtv = new DTVaccine();
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(null);

    vaccineData.saveVaccine(dtv);
  }

  @Test
  public void createVaccineTestWithVaccineId() {
    DTVaccine dtv = new DTVaccine();
    DTDisease dtd = new DTDisease();
    DTLaboratory dtl = new DTLaboratory();
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(modelMapper.map(dtl, Laboratory.class)).thenReturn(laboratory);
    when(vaccine.getId()).thenReturn(1L);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getCreateDate()).thenReturn(new Date());

    vaccineData.createVaccine(dtv, dtd, null);
  }

  @Test
  public void createVaccineTestWithoutVaccineId() {
    DTVaccine dtv = new DTVaccine();
    DTDisease dtd = new DTDisease();
    DTLaboratory dtl = new DTLaboratory();
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(modelMapper.map(dtl, Laboratory.class)).thenReturn(laboratory);
    when(vaccine.getId()).thenReturn(null);

    vaccineData.createVaccine(dtv, dtd, null);
  }

  @Test
  public void getVaccineByIdTest() {
    DTVaccine dtv = new DTVaccine();
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getDTVaccine()).thenReturn(dtv);

    vaccineData.getVaccineById(1L);
  }

  @Test
  public void deleteVaccineTest() {
    DTVaccine dtv = new DTVaccine();
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getDTVaccine()).thenReturn(dtv);
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(vaccine.getId()).thenReturn(1L);

    vaccineData.deleteVaccine(1L);
  }

  @Test
  public void listVaccinesTest() {
    List<Vaccine> lV = new ArrayList<>();
    DTVaccine dtv = new DTVaccine();
    lV.add(vaccine);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lV);
    when(vaccine.getDTVaccine()).thenReturn(dtv);
    when(vaccine.getLaboratory()).thenReturn(laboratory);
    when(laboratory.getName()).thenReturn("Test");
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");

    vaccineData.listVaccines();
  }

  @Test
  public void addDiseaseToVaccineTest() {
    DTDisease dtd = new DTDisease();
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);

    vaccineData.addDiseaseToVaccine(1L, dtd);
  }

  @Test
  public void addLaboratoryToVaccineTest() {
    DTLaboratory dtl = new DTLaboratory();
    when(modelMapper.map(dtl, Laboratory.class)).thenReturn(laboratory);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);

    vaccineData.addLaboratoryToVaccine(1L, dtl);
  }

  @Test
  public void getVaccineReportTestWithAllData() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    List<DTPackage> lDtp = new ArrayList<>();
    DTPackage dtp = new DTPackage();
    dtp.setId(1L);
    lDtp.add(dtp);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getBatches()).thenReturn(lB);
    when(batch.getId()).thenReturn(1L);
    when(iPackageController.getPackagesByBatchId(1L)).thenReturn(lDtp);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(vaccine.getName()).thenReturn("Test");
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");
    when(batch.getNumber()).thenReturn(1L);
    when(batch.getAvailable()).thenReturn(1);
    when(aPackage.getVaccinationPlan()).thenReturn(vaccinationPlan);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(aPackage.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(aPackage.getQuantity()).thenReturn(1L);
    when(aPackage.getPackageNumber()).thenReturn(1L);
    when(aPackage.getPackageState()).thenReturn(PackageState.Delivered);

    vaccineData.getVaccineReport(1L);
  }

  @Test
  public void getVaccineReportTestWithoutVaccinationPlan() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    List<DTPackage> lDtp = new ArrayList<>();
    DTPackage dtp = new DTPackage();
    dtp.setId(1L);
    lDtp.add(dtp);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getBatches()).thenReturn(lB);
    when(batch.getId()).thenReturn(1L);
    when(iPackageController.getPackagesByBatchId(1L)).thenReturn(lDtp);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(vaccine.getName()).thenReturn("Test");
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");
    when(batch.getNumber()).thenReturn(1L);
    when(batch.getAvailable()).thenReturn(1);
    when(aPackage.getVaccinationPlan()).thenReturn(null);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(aPackage.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(aPackage.getQuantity()).thenReturn(1L);
    when(aPackage.getPackageNumber()).thenReturn(1L);
    when(aPackage.getPackageState()).thenReturn(PackageState.Delivered);

    vaccineData.getVaccineReport(1L);
  }

  @Test
  public void getVaccineReportTestWithoutVaccinationCenter() {
    List<Batch> lB = new ArrayList<>();
    lB.add(batch);
    List<DTPackage> lDtp = new ArrayList<>();
    DTPackage dtp = new DTPackage();
    dtp.setId(1L);
    lDtp.add(dtp);
    when(data.find(Vaccine.class, 1L)).thenReturn(vaccine);
    when(vaccine.getBatches()).thenReturn(lB);
    when(batch.getId()).thenReturn(1L);
    when(iPackageController.getPackagesByBatchId(1L)).thenReturn(lDtp);
    when(data.find(Package.class, 1L)).thenReturn(aPackage);
    when(vaccine.getName()).thenReturn("Test");
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");
    when(batch.getNumber()).thenReturn(1L);
    when(batch.getAvailable()).thenReturn(1);
    when(aPackage.getVaccinationPlan()).thenReturn(null);
    when(vaccinationPlan.getName()).thenReturn("Test");
    when(aPackage.getVaccinationCenter()).thenReturn(null);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(aPackage.getQuantity()).thenReturn(1L);
    when(aPackage.getPackageNumber()).thenReturn(1L);
    when(aPackage.getPackageState()).thenReturn(PackageState.Delivered);

    vaccineData.getVaccineReport(1L);
  }
}
