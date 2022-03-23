package DAL;

import DTO.DTAdministrator;
import DTO.DTPerson;
import entities.Administrator;
import entities.Person;
import entities.Role;
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
public class AdministratorDataTest {

  private AdministratorData administratorData;

  private Administrator administrator;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  private Person person;

  private Role role;

  @Before
  public void setup() {
    role = mock(Role.class);
    person = mock(Person.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    data = mock(EntityManager.class);
    administrator = mock(Administrator.class);
    modelMapper = mock(ModelMapper.class);
    this.administratorData = new AdministratorData();
    this.administratorData.setData(data);
    this.administratorData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(person);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(administrator);
    Mockito.reset(role);
  }

  @Test
  public void saveAdministratorTestWithAdministratorDeleted() {
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    DTAdministrator dta = new DTAdministrator();
    dta.setDtPerson(dtp);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("administrator");
    when(role.getDeleteDate()).thenReturn(new Date());
    when(role.getId()).thenReturn(1L);
    when(data.find(Administrator.class, 1L)).thenReturn(administrator);
    when(administrator.getDTAdministrator()).thenReturn(new DTAdministrator());

    administratorData.saveAdministrator(dta);
  }

  @Test
  public void saveAdministratorTestWithNewAdministrator() {
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    DTAdministrator dta = new DTAdministrator();
    dta.setDtPerson(dtp);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("administrator");
    when(role.getDeleteDate()).thenReturn(null);
    when(role.getId()).thenReturn(1L);
    when(data.find(Administrator.class, 1L)).thenReturn(administrator);
    when(administrator.getDTAdministrator()).thenReturn(new DTAdministrator());

    administratorData.saveAdministrator(dta);
  }

  @Test
  public void saveAdministratorTestWithoutAdministrator() {
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    DTAdministrator dta = new DTAdministrator();
    dta.setDtPerson(dtp);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("Test");
    when(role.getDeleteDate()).thenReturn(null);

    administratorData.saveAdministrator(dta);
  }

  @Test
  public void getAdministratorsTest() {
    List<Administrator> lA = new ArrayList<>();
    lA.add(administrator);
    when(data.createQuery("select a from Administrator a ")).thenReturn(query);
    when(query.getResultList()).thenReturn(lA);
    when(administrator.getDTAdministrator()).thenReturn(new DTAdministrator());

    administratorData.getAdministrators();
  }

  @Test
  public void getByIdAdministratorTest() {
    when(data.find(Administrator.class, 1L)).thenReturn(administrator);
    when(administrator.getDTAdministrator()).thenReturn(new DTAdministrator());

    administratorData.getByIdAdministrator(1L);
  }

  @Test
  public void deleteAdministratorTest() {
    when(data.find(Administrator.class, 1L)).thenReturn(administrator);

    administratorData.deleteAdministrator(1L);
  }
}
