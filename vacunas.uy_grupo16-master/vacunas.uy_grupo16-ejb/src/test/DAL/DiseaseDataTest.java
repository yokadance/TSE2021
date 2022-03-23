package DAL;

import DTO.DTDisease;
import DTO.DTVaccine;
import entities.Disease;
import entities.Vaccine;
import org.junit.After;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiseaseDataTest {

  DiseaseData diseaseData;

  Disease disease;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Vaccine vaccine;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    disease = mock(Disease.class);
    this.diseaseData = new DiseaseData();
    this.diseaseData.setData(data);
    this.diseaseData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(disease);
    Mockito.reset(vaccine);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveDiseaseTestWithIdNull() {
    DTDisease dtd = new DTDisease();
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(disease.getId()).thenReturn(null);

    diseaseData.saveDisease(dtd);
  }

  @Test
  public void saveDiseaseTestWithIdNotNull() {
    DTDisease dtd = new DTDisease();
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(disease.getId()).thenReturn(1L);
    when(disease.getCreateDate()).thenReturn(new Date());
    when(data.find(Disease.class, 1L)).thenReturn(disease);

    diseaseData.saveDisease(dtd);
  }

  @Test
  public void getDiseaseByIdTest() {
    DTDisease dtd = new DTDisease();
    when(data.find(Disease.class, 1L)).thenReturn(disease);
    when(disease.getDTDisease()).thenReturn(dtd);

    diseaseData.getDiseaseById(1L);
  }

  @Test
  public void deleteDiseaseTest() {
    DTDisease dtd = new DTDisease();
    when(data.find(Disease.class, 1L)).thenReturn(disease);
    when(disease.getDTDisease()).thenReturn(dtd);
    when(modelMapper.map(dtd, Disease.class)).thenReturn(disease);
    when(disease.getId()).thenReturn(1L);

    diseaseData.deleteDisease(1L);
  }

  @Test
  public void listDiseasesTest() {
    List<Disease> lD = new ArrayList<>();
    lD.add(disease);
    DTDisease dtd = new DTDisease();
    when(data.createQuery("select v from Disease v where v.deleteDate is null")).thenReturn(query);
    when(query.getResultList()).thenReturn(lD);
    when(disease.getDTDisease()).thenReturn(dtd);

    diseaseData.listDiseases();
  }

  @Test
  public void getDiseaseByNameTest() {
    List<Disease> lD = new ArrayList<>();
    lD.add(disease);
    DTDisease dtd = new DTDisease();
    when(data.createQuery("select v from Disease v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)")).thenReturn(typedQuery);
    when(typedQuery.setParameter("name", "%Test%")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lD);
    when(disease.getDTDisease()).thenReturn(dtd);

    diseaseData.getDiseaseByName("Test");
  }

  @Test
  public void addVaccineToDiseaseTest() {
    DTVaccine dtv = new DTVaccine();
    List<Vaccine> lV = new ArrayList<>();
    lV.add(vaccine);
    when(modelMapper.map(dtv, Vaccine.class)).thenReturn(vaccine);
    when(data.find(Disease.class, 1L)).thenReturn(disease);
    when(disease.getVaccine()).thenReturn(lV);

    diseaseData.addVaccineToDisease(1L, dtv);
  }

  @Test
  public void getDiseaseIdByNameTest() {
    List<Disease> lD = new ArrayList<>();
    lD.add(disease);
    DTDisease dtd = new DTDisease();
    dtd.setId(1L);
    when(data.createQuery("select v from Disease v where v.deleteDate is null and LOWER(v.name) like LOWER(:name)")).thenReturn(typedQuery);
    when(typedQuery.setParameter("name", "%Test%")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lD);
    when(disease.getDTDisease()).thenReturn(dtd);

    diseaseData.getDiseaseIdByName("Test");
  }
}
