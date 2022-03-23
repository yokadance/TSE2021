package DAL;

import DTO.DTAuthority;
import DTO.DTPerson;
import entities.Authority;
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
public class AuthorityDataTest {

  private AuthorityData authorityData;

  private Authority authority;

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
    authority = mock(Authority.class);
    modelMapper = mock(ModelMapper.class);
    this.authorityData = new AuthorityData();
    this.authorityData.setData(data);
    this.authorityData.setModelMapper(modelMapper);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(person);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
    Mockito.reset(role);
  }

  @Test
  public void saveAuthorityTest() {
    DTAuthority dta = new DTAuthority();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dta.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("authority");
    when(role.getDeleteDate()).thenReturn(new Date());
    when(role.getId()).thenReturn(1L);
    when(data.find(Authority.class, 1L)).thenReturn(authority);
    when(authority.getDTAuthority()).thenReturn(new DTAuthority());

    authorityData.saveAuthority(dta);
  }

  @Test
  public void saveAuthorityTestWithDeletedAuthority() {
    DTAuthority dta = new DTAuthority();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dta.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("authority");
    when(role.getDeleteDate()).thenReturn(new Date());
    when(role.getId()).thenReturn(1L);
    when(data.find(Authority.class, 1L)).thenReturn(authority);
    when(authority.getDTAuthority()).thenReturn(new DTAuthority());

    authorityData.saveAuthority(dta);
  }

  @Test
  public void saveAuthorityTestWithNotDeletedAuthority() {
    DTAuthority dta = new DTAuthority();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dta.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("authority");
    when(role.getDeleteDate()).thenReturn(null);
    when(role.getId()).thenReturn(1L);
    when(data.find(Authority.class, 1L)).thenReturn(authority);
    when(authority.getDTAuthority()).thenReturn(new DTAuthority());

    authorityData.saveAuthority(dta);
  }

  @Test
  public void saveAuthorityTestWithNewAuthority() {
    DTAuthority dta = new DTAuthority();
    DTPerson dtp = new DTPerson();
    dtp.setId(1L);
    dta.setDtPerson(dtp);
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select p from Person p where p.id = :id", Person.class)).thenReturn(typedQuery);
    when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(person);
    when(person.getRoles()).thenReturn(lR);
    when(role.getName()).thenReturn("Test");
    when(role.getDeleteDate()).thenReturn(null);

    authorityData.saveAuthority(dta);
  }

  @Test
  public void getAuthorityTest() {
    List<Authority> lA = new ArrayList<>();
    lA.add(authority);
    when(data.createQuery("select a from Authority a ")).thenReturn(query);
    when(query.getResultList()).thenReturn(lA);
    when(authority.getDTAuthority()).thenReturn(new DTAuthority());

    authorityData.getAuthority();
  }

  @Test
  public void getByIdAuthorityTest() {
    when(data.find(Authority.class, 1L)).thenReturn(authority);
    when(authority.getDTAuthority()).thenReturn(new DTAuthority());

    authorityData.getByIdAuthority(1L);
  }

  @Test
  public void deleteAuthorityTest() {
    when(data.find(Authority.class, 1L)).thenReturn(authority);

    authorityData.deleteAuthority(1L);
  }
}
