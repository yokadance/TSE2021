package DAL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import DTO.DTVaccinationAct;
import entities.*;
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
public class VaccinationActDataTest {

  private VaccinationActData vaccinationActData;

  private VaccinationAct vaccinationAct;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private VaccinationPost vaccinationPost;

  private Citizen citizen;

  private Person person;

  private VaccinationCenter vaccinationCenter;

  private Package aPackage;

  private Batch batch;

  private Vaccine vaccine;

  private Disease disease;

  @Before
  public void setup() {
    vaccine = mock(Vaccine.class);
    disease = mock(Disease.class);
    batch = mock(Batch.class);
    aPackage = mock(Package.class);
    vaccinationCenter = mock(VaccinationCenter.class);
    person = mock(Person.class);
    citizen = mock(Citizen.class);
    vaccinationPost = mock(VaccinationPost.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    vaccinationAct = mock(VaccinationAct.class);
    this.vaccinationActData = new VaccinationActData();
    this.vaccinationActData.setData(data);
    this.vaccinationActData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(vaccine);
    Mockito.reset(batch);
    Mockito.reset(aPackage);
    Mockito.reset(vaccinationCenter);
    Mockito.reset(person);
    Mockito.reset(citizen);
    Mockito.reset(vaccinationPost);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(disease);
  }

  @Test
  public void saveVaccinationActTestWithId() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    when(modelMapper.map(dtva, VaccinationAct.class)).thenReturn(vaccinationAct);
    when(vaccinationAct.getId()).thenReturn(1L);
    when(vaccinationAct.getCreateDate()).thenReturn(new Date());
    when(data.find(VaccinationAct.class, 1L)).thenReturn(vaccinationAct);

    vaccinationActData.saveVaccinationAct(dtva);
  }

  @Test
  public void saveVaccinationActWithoutId() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    when(modelMapper.map(dtva, VaccinationAct.class)).thenReturn(vaccinationAct);
    when(vaccinationAct.getId()).thenReturn(null);

    vaccinationActData.saveVaccinationAct(dtva);
  }

  @Test
  public void getVaccinationActByIdTest() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    when(data.find(VaccinationAct.class, 1L)).thenReturn(vaccinationAct);
    when(vaccinationAct.getDTVaccinationAct()).thenReturn(dtva);

    vaccinationActData.getVaccinationActById(1L);
  }

  @Test
  public void deleteVaccinationActTest() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    when(data.find(VaccinationAct.class, 1L)).thenReturn(vaccinationAct);
    when(vaccinationAct.getDTVaccinationAct()).thenReturn(dtva);
    when(data.find(VaccinationAct.class, 1L)).thenReturn(vaccinationAct);
    when(vaccinationAct.getDTVaccinationAct()).thenReturn(dtva);

    vaccinationActData.deleteVaccinationAct(1L);
  }

  @Test
  public void listVaccinationActTest() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    List<VaccinationAct> lVa = new ArrayList<>();
    lVa.add(vaccinationAct);
    when(data.createQuery("select v from VaccinationAct v")).thenReturn(query);
    when(query.getResultList()).thenReturn(lVa);
    when(vaccinationAct.getDTVaccinationAct()).thenReturn(dtva);

    vaccinationActData.listVaccinationAct();
  }

  @Test
  public void vaccinationActByCiTest() {
    DTVaccinationAct dtva = new DTVaccinationAct();
    List<VaccinationAct> lVa = new ArrayList<>();
    lVa.add(vaccinationAct);
    when(data.createQuery("select v from VaccinationAct v where v.citizen.person.ci=:ci")).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lVa);
    when(vaccinationAct.getCitizen()).thenReturn(citizen);
    when(citizen.getPerson()).thenReturn(person);
    when(person.getName()).thenReturn("Test");
    when(person.getLastname()).thenReturn("Test");
    when(person.getSurname()).thenReturn("Test");
    when(vaccinationAct.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(vaccinationAct.getaPackage()).thenReturn(aPackage);
    when(aPackage.getBatch()).thenReturn(batch);
    when(batch.getVaccine()).thenReturn(vaccine);
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");
    when(vaccine.getName()).thenReturn("Test");
    when(person.getCi()).thenReturn("Test");
    when(person.getBirthday()).thenReturn(new Date());
    when(vaccinationAct.getCreateDate()).thenReturn(new Date());

    vaccinationActData.vaccinationActByCi("Test");
  }

  @Test
  public void getVaccinationActsViewTest() {
    List<VaccinationAct> lVa = new ArrayList<>();
    lVa.add(vaccinationAct);
    when(data.createQuery("select v from VaccinationAct v ")).thenReturn(query);
    when(query.getResultList()).thenReturn(lVa);
    when(vaccinationAct.getCitizen()).thenReturn(citizen);
    when(citizen.getPerson()).thenReturn(person);
    when(person.getName()).thenReturn("Test");
    when(person.getLastname()).thenReturn("Test");
    when(person.getSurname()).thenReturn("Test");
    when(vaccinationAct.getVaccinationPost()).thenReturn(vaccinationPost);
    when(vaccinationPost.getVaccinationCenter()).thenReturn(vaccinationCenter);
    when(vaccinationCenter.getName()).thenReturn("Test");
    when(vaccinationAct.getaPackage()).thenReturn(aPackage);
    when(aPackage.getBatch()).thenReturn(batch);
    when(batch.getVaccine()).thenReturn(vaccine);
    when(vaccine.getName()).thenReturn("Test");
    when(person.getCi()).thenReturn("Test");
    when(person.getBirthday()).thenReturn(new Date());
    when(vaccinationAct.getCreateDate()).thenReturn(new Date());
    when(vaccine.getDisease()).thenReturn(disease);
    when(disease.getName()).thenReturn("Test");

    vaccinationActData.getVaccinationActsView();
  }
}
