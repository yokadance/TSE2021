package DAL;

import DTO.DTAdministrator;
import DTO.DTAuthority;
import DTO.DTCitizen;
import DTO.DTVaccinator;
import IDAL.IAdministratorData;
import IDAL.IAuthorityData;
import IDAL.ICitizenData;
import IDAL.IVaccinatorData;
import entities.Citizen;
import entities.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
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
public class RoleDataTest {

  private RoleData roleData;

  private Role role;

  private EntityManager data;

  private ModelMapper modelMapper;

  private Query query;

  private TypedQuery typedQuery;

  @Spy
  ICitizenData iCitizenData;

  @Spy
  IAuthorityData iAuthorityData;

  @Spy
  IVaccinatorData iVaccinatorData;

  @Spy
  IAdministratorData iAdministratorData;

  private Citizen citizen;

  @Before
  public void setup() {
    citizen = mock(Citizen.class);
    typedQuery = mock(TypedQuery.class);
    query = mock(Query.class);
    role = mock(Role.class);
    data = mock(EntityManager.class);
    modelMapper = mock(ModelMapper.class);
    this.roleData = new RoleData();
    this.roleData.setData(data);
    this.roleData.setModelMapper(modelMapper);
    this.roleData.setiAdministratorData(iAdministratorData);
    this.roleData.setiCitizenData(iCitizenData);
    this.roleData.setiVaccinatorData(iVaccinatorData);
    this.roleData.setiAuthorityData(iAuthorityData);
  }

  @After
  public void teardown() {
    Mockito.reset(data);
    Mockito.reset(modelMapper);
    Mockito.reset(citizen);
    Mockito.reset(role);
    Mockito.reset(typedQuery);
    Mockito.reset(query);
  }

  @Test
  public void getByIdRoleTest() {
    when(data.find(Role.class, 1L)).thenReturn(role);
    when(role.getDTRole()).thenReturn(new DTCitizen());

    roleData.getByIdRole(1L);
  }

  @Test
  public void saveRoleTestWithId() {
    DTCitizen dtc = new DTCitizen();
    when(modelMapper.map(dtc, Citizen.class)).thenReturn(citizen);
    when(citizen.getId()).thenReturn(1L);
    when(data.find(Citizen.class, 1L)).thenReturn(citizen);
    when(citizen.getCreateDate()).thenReturn(new Date());

    roleData.saveRole(dtc);
  }

  @Test
  public void saveRoleTestWithoutId() {
    DTCitizen dtc = new DTCitizen();
    when(modelMapper.map(dtc, Citizen.class)).thenReturn(citizen);
    when(citizen.getId()).thenReturn(null);

    roleData.saveRole(dtc);
  }

  @Test
  public void getRolesTest() {
    List<Role> lR = new ArrayList<>();
    lR.add(role);
    when(data.createQuery("select r from Role r")).thenReturn(query);
    when(query.getResultList()).thenReturn(lR);
    when(role.getDTRole()).thenReturn(new DTCitizen());

    roleData.getRoles();
  }

  @Test
  public void deleteRoleTestCitizen() {
    DTCitizen dtc = new DTCitizen();
    dtc.setId(1L);

    roleData.deleteRole(dtc);
  }

  @Test
  public void deleteRoleTestAdministrator() {
    DTAdministrator dta = new DTAdministrator();
    dta.setId(1L);

    roleData.deleteRole(dta);
  }

  @Test
  public void deleteRoleTestAuthority() {
    DTAuthority dta = new DTAuthority();
    dta.setId(1L);

    roleData.deleteRole(dta);
  }

  @Test
  public void deleteRoleTestVaccinator() {
    DTVaccinator dtv = new DTVaccinator();
    dtv.setId(1L);

    roleData.deleteRole(dtv);
  }

  @Test
  public void saveAnyRoleTestCitizen() {
    DTCitizen dtc = new DTCitizen();
    dtc.setId(1L);

    roleData.saveAnyRole(dtc);
  }

  @Test
  public void saveAnyRoleTestAdministrator() {
    DTAdministrator dta = new DTAdministrator();
    dta.setId(1L);

    roleData.saveAnyRole(dta);
  }

  @Test
  public void saveAnyRoleTestAuthority() {
    DTAuthority dta = new DTAuthority();
    dta.setId(1L);

    roleData.saveAnyRole(dta);
  }

  @Test
  public void saveAnyRoleTestVaccinator() {
    DTVaccinator dtv = new DTVaccinator();
    dtv.setId(1L);

    roleData.saveAnyRole(dtv);
  }
}
