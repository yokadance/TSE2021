package DAL;

import DTO.DTCitizen;
import DTO.DTPerson;
import entities.Citizen;
import entities.Person;
import entities.Role;
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
public class CitizenDataTest {

  private CitizenData citizenData;

  private Citizen citizen;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Person person;

  private Role role;

  @Before
  public void setup() {
    person = mock(Person.class);
    citizen = mock(Citizen.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    role = mock(Role.class);
    this.citizenData = new CitizenData();
    this.citizenData.setData(data);
    this.citizenData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(person);
    Mockito.reset(citizen);
    Mockito.reset(role);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void saveCitizenTestWithCitizenDeleted() {
    DTCitizen dtc = new DTCitizen();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtc.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("citizen");
    when(role.getDeleteDate()).thenReturn(new Date());
    when(role.getId()).thenReturn(1L);
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);

    citizenData.saveCitizen(dtc);
  }

  @Test
  public void saveCitizenTestWithNewCitizen() {
    DTCitizen dtc = new DTCitizen();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtc.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("citizen");
    when(role.getDeleteDate()).thenReturn(null);
    when(role.getId()).thenReturn(1L);
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);

    citizenData.saveCitizen(dtc);
  }

  @Test
  public void saveCitizenTestWithRoleNoCitizen() {
    DTCitizen dtc = new DTCitizen();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dtc.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("Test");
    when(role.getDeleteDate()).thenReturn(null);
    when(role.getId()).thenReturn(1L);
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);

    citizenData.saveCitizen(dtc);
  }

  @Test
  public void getCitizensTest() {
    DTCitizen dtc = new DTCitizen();
    List<Citizen> lC = new ArrayList<>();
    lC.add(citizen);
    when(data.createQuery(anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(lC);
    when(citizen.getDTCitizen()).thenReturn(dtc);

    citizenData.getCitizens();
  }

  @Test
  public void getByIdCitizenTest() {
    DTCitizen dtc = new DTCitizen();
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);
    when(citizen.getDTCitizen()).thenReturn(dtc);

    citizenData.getByIdCitizen(1L);
  }

  @Test
  public void deleteCitizenTest() {
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);

    citizenData.deleteCitizen(1L);
  }

  @Test
  public void getCitizenIdByCiTest() {
    List<Citizen> lC = new ArrayList<>();
    lC.add(citizen);
    when(data.createQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(1L);

    citizenData.getCitizenIdByCi("Test");
  }

  @Test
  public void getCitizenByCiTest() {
    DTCitizen dtc = new DTCitizen();
    when(data.createQuery("select c from Citizen c where c.person.ci=:ci", Citizen.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(citizen);
    when(citizen.getDTCitizen()).thenReturn(dtc);

    citizenData.getCitizenByCi("Test");
  }

  @Test
  public void setTokenTestReturnTrue() {
    DTCitizen dtc = new DTCitizen();
    when(data.createQuery("select c from Citizen c where c.person.ci=:ci", Citizen.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(citizen);
    when(citizen.getDTCitizen()).thenReturn(dtc);

    citizenData.setToken("Test", "Test");
    Assert.assertEquals(true, citizenData.setToken("Test", "Test"));
  }

  @Test
  public void setTokenTestReturnFalse() {
    DTCitizen dtc = new DTCitizen();
    when(data.createQuery("select c from Citizen c where c.person.ci=:ci", Citizen.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("ci", "Test")).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(null);

    citizenData.setToken("Test", "Test");
    Assert.assertEquals(false, citizenData.setToken("Test", "Test"));
  }
}
