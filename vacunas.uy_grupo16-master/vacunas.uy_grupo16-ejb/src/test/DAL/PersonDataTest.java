package DAL;

import DTO.*;
import entities.*;
import enumerations.Sex;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonDataTest {

  private PersonData personData;

  private Person person;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Citizen citizen;

  private Administrator administrator;

  private Authority authority;

  private Vaccinator vaccinator;

  private Role role;

  @Before
  public void setup() {
    role = mock(Role.class);
    administrator = mock(Administrator.class);
    authority = mock(Authority.class);
    vaccinator = mock(Vaccinator.class);
    citizen = mock(Citizen.class);
    person = mock(Person.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.personData = new PersonData();
    this.personData.setData(data);
    this.personData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(person);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(citizen);
    Mockito.reset(administrator);
    Mockito.reset(authority);
    Mockito.reset(vaccinator);
    Mockito.reset(role);
  }

  @Test
  public void savePersonTestOk() {
    String ci = "Test";
    Person p2 = new Person();
    p2.setCi(ci);
    p2.setBirthday(new Date());
    p2.setEmail("Test");
    p2.setSex(Sex.feminine);
    p2.setSex(Sex.masculine);
    p2.setSex(Sex.other);
    p2.setName("Test");
    p2.setLastname("Test");
    DTPerson dtp = new DTPerson();
    dtp.setCi(ci);
    person.setId(1L);
    person.setCi(ci);
    when(modelMapper.map(dtp, Person.class)).thenReturn(p2);
    when(person.getId()).thenReturn(1L);
    when(data.createQuery("select p from Person p where p.ci = :ci", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);

    personData.savePerson(dtp);
  }

  @Test
  public void savePersonTestCatchException2() {
    DTPerson dtp = new DTPerson();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(data.find(any(), any())).thenReturn(typedQuery);
    when(person.getCi()).thenReturn("Test");
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getBirthday()).thenReturn(new Date());
    when(person.getEmail()).thenReturn("Test");
    when(person.getSex()).thenReturn(Sex.feminine);
    when(person.getSex()).thenReturn(Sex.masculine);
    when(person.getSex()).thenReturn(Sex.other);

    personData.savePerson(dtp);
  }

  @Test
  public void createPersonCitizenTest() {
    DTPerson dtp = new DTPerson();
    DTCitizen dtc = new DTCitizen();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(modelMapper.map(dtc, Citizen.class)).thenReturn(citizen);
    when(citizen.getId()).thenReturn(1L);
    when(data.getReference(Citizen.class, 1L)).thenReturn(citizen);

    personData.createPersonCitizen(dtp, dtc);
  }

  @Test
  public void createPersonAdministratorTest() {
    DTPerson dtp = new DTPerson();
    DTAdministrator dta = new DTAdministrator();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(modelMapper.map(dta, Administrator.class)).thenReturn(administrator);
    when(administrator.getId()).thenReturn(1L);
    when(data.getReference(Administrator.class, 1L)).thenReturn(administrator);

    personData.createPersonAdministrator(dtp, dta);
  }

  @Test
  public void createPersonAuthorityTestWithPersonIdNotNull() {
    DTPerson dtp = new DTPerson();
    DTAuthority dta = new DTAuthority();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(modelMapper.map(dta, Authority.class)).thenReturn(authority);
    when(administrator.getId()).thenReturn(1L);
    when(data.getReference(Authority.class, 1L)).thenReturn(authority);

    personData.createPersonAuthority(dtp, dta);
  }

  @Test
  public void createPersonAuthorityTestWithPersonIdNull() {
    DTPerson dtp = new DTPerson();
    dtp.setId(null);
    DTAuthority dta = new DTAuthority();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(modelMapper.map(dta, Authority.class)).thenReturn(authority);
    when(administrator.getId()).thenReturn(1L);
    when(data.getReference(Authority.class, 1L)).thenReturn(authority);

    personData.createPersonAuthority(dtp, dta);
  }

  @Test
  public void createPersonVaccinatorTest() {
    DTPerson dtp = new DTPerson();
    DTVaccinator dtv = new DTVaccinator();
    when(modelMapper.map(dtp, Person.class)).thenReturn(person);
    when(modelMapper.map(dtv, Vaccinator.class)).thenReturn(vaccinator);
    when(vaccinator.getId()).thenReturn(1L);
    when(data.getReference(Vaccinator.class, 1L)).thenReturn(vaccinator);

    personData.createPersonVaccinator(dtp, dtv);
  }

  @Test
  public void getByIdPersonTest() {
    DTPerson dtp = new DTPerson();
    Long id = 1L;
    when(data.find(Person.class, id)).thenReturn(person);
    when(person.getDTPerson()).thenReturn(dtp);

    personData.getByIdPerson(id);
  }

  @Test
  public void getPersonsTest() {
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    Long id = 1L;
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);

    personData.getPersons();
  }

  @Test
  public void getBasicPersonsTest() {
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    Long id = 1L;
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);

    personData.getBasicPersons();
  }

  @Test
  public void deletePersonTest() {
    DTPerson dtp = new DTPerson();
    Long id = 1L;
    when(data.find(Person.class, id)).thenReturn(person);

    personData.deletePerson(id);
  }

  @Test
  public void getPersonByCITestReturnPerson() throws Exception {
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    String ci = "Test";
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lp);

    personData.getPersonByCI(ci);
  }

  @Test
  public void getPersonByCITestReturnNullDto() throws Exception {
    List<Person> lp = null;
    String ci = "Test";
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lp);

    personData.getPersonByCI(ci);
  }

  @Test
  public void existPersonByCITestReturnFalse() throws Exception {
    List<Person> lp = null;
    String ci = "Test";
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(null);
    when(personData.getPersonByCI(ci)).thenReturn(null);

    personData.existPersonByCI(ci);
    //    Assert.assertEquals(false, personData.existPersonByCI(ci));
  }

  @Test
  public void existPersonByCITestReturnTrue() throws Exception {
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    DTPerson dtp = new DTPerson();
    String ci = "Test";
    when(data.createQuery(anyString(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", ci)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(lp);
    when(personData.getPersonByCI(ci)).thenReturn(dtp);

    personData.existPersonByCI(ci);
    Assert.assertEquals(true, personData.existPersonByCI(ci));
  }

  @Test
  public void getUserRolesTest() {
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    person.setRoles(new ArrayList<>());
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    String ci = "Test";
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.setParameter("ci", ci)).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("Test");

    personData.getUserRoles(ci);
  }

  @Test
  public void getDtRolesFromUserTest() {
    DTCitizen dtc = new DTCitizen();
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    person.setRoles(new ArrayList<>());
    List<Person> lp = new ArrayList<>();
    lp.add(person);
    String ci = "Test";
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.setParameter("ci", ci)).thenReturn(query);
    when(query.getResultList()).thenReturn(lp);
    when(person.getRoles()).thenReturn(lR);
    when(role.getDTRole()).thenReturn(dtc);

    personData.getDtRolesFromUser(ci);
  }
}
