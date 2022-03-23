package Controllers;

import Controller.PersonController;
import DTO.DTCitizen;
import DTO.DTNewPerson;
import DTO.DTPerson;
import IDAL.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTests {

  @Spy
  IPersonData iPersonData;

  @Spy
  ICitizenData iCitizenData;

  @Spy
  IAdministratorData iAdministratorData;

  @Spy
  IAuthorityData iAuthorityData;

  @Spy
  IVaccinatorData iVaccinatorData;

  @Spy
  DirContext dirContext;

  private PersonController personController;

  @Before
  public void setup() {
    this.iPersonData = mock(IPersonData.class);
    this.iCitizenData = mock(ICitizenData.class);
    this.iAdministratorData = mock(IAdministratorData.class);
    this.iAuthorityData = mock(IAuthorityData.class);
    this.iVaccinatorData = mock(IVaccinatorData.class);
    this.dirContext = mock(DirContext.class);
    this.personController = new PersonController();
    this.personController.setiPersonData(iPersonData);
    this.personController.setiCitizenData(iCitizenData);
    this.personController.setiAdministratorData(iAdministratorData);
    this.personController.setiAuthorityData(iAuthorityData);
    this.personController.setiVaccinatorData(iVaccinatorData);
    this.personController.setDctx(dirContext);
  }

  @After
  public void teardown() {
    Mockito.reset(iPersonData);
    Mockito.reset(iCitizenData);
    Mockito.reset(iAdministratorData);
    Mockito.reset(iAuthorityData);
    Mockito.reset(iVaccinatorData);
  }

  @Test
  public void savePersonTest() {
    DTPerson dtp = new DTPerson();
    personController.savePerson(dtp);
    Mockito.verify(iPersonData, times(1)).savePerson(dtp);
  }

  @Test
  public void getUserDNTest() {
    personController.getUserDN("Test");
  }

  @Test
  public void existsLdapUserTestWithUserNoNull() throws NamingException {
    Assert.assertEquals(false, personController.existsLdapUser("Test"));
  }

  @Test
  public void authenticateLDAPTest() throws Exception {
    Assert.assertEquals(false, personController.authenticateLDAP("Test", "Test"));
  }

  @Test
  public void getPersonTest() {
    personController.getPerson(anyLong());
    Mockito.verify(iPersonData, times(1)).getByIdPerson(anyLong());
  }

  @Test
  public void getPersonsTest() {
    personController.getPersons();
    Mockito.verify(iPersonData, times(1)).getPersons();
  }

  @Test
  public void getBasicPersonsTest() {
    personController.getBasicPersons();
    Mockito.verify(iPersonData, times(1)).getBasicPersons();
  }

  @Test
  public void deletePersonTest() {
    personController.deletePerson(anyLong());
    Mockito.verify(iPersonData, times(1)).deletePerson(anyLong());
  }

  @Test
  public void getPersonByCITest() throws Exception {
    personController.getPersonByCI("Test");
    Mockito.verify(iPersonData, times(1)).getPersonByCI(anyString());
  }

  @Test
  public void existPersonByCITest() throws Exception {
    personController.existPersonByCI("Test");
    Mockito.verify(iPersonData, times(1)).existPersonByCI(anyString());
  }

  @Test
  public void getUserRolesTest() throws Exception {
    personController.getUserRoles("Test");
    Mockito.verify(iPersonData, times(1)).getUserRoles(anyString());
  }

  @Test
  public void getDtRolesFromUserTest() {
    personController.getDtRolesFromUser("Test");
    Mockito.verify(iPersonData, times(1)).getDtRolesFromUser(anyString());
  }

  @Test
  public void createPersonTestReturn0() {
    DTCitizen dtc = new DTCitizen();
    DTNewPerson dtnp = new DTNewPerson();
    dtnp.setCi("Test");
    dtnp.setBirthday(new Date());
    dtnp.setIdUruguay("Test");
    dtnp.setSecondlastname("0");
    dtnp.setEmail("Test");
    dtnp.setUserid("Test");
    dtnp.setRoles("Citizen");
    when(iCitizenData.saveCitizen(any())).thenReturn(dtc);

    Assert.assertEquals(0, personController.createPerson(dtnp));
  }

  @Test
  public void createPersonTestWhenPersonIsCitizen() {
    DTCitizen dtc = new DTCitizen();
    DTNewPerson dtnp = new DTNewPerson();
    dtnp.setCi("Test");
    dtnp.setBirthday(new Date());
    dtnp.setIdUruguay("Test");
    dtnp.setSecondlastname("0");
    dtnp.setEmail("Test");
    dtnp.setUserid("Test");
    dtnp.setRoles("citizen");
    when(iCitizenData.saveCitizen(any())).thenReturn(dtc);

    Assert.assertEquals(0, personController.createPerson(dtnp));
  }

  @Test
  public void createPersonTestWhenPersonIsAdministrator() {
    DTCitizen dtc = new DTCitizen();
    DTNewPerson dtnp = new DTNewPerson();
    dtnp.setCi("Test");
    dtnp.setBirthday(new Date());
    dtnp.setIdUruguay("Test");
    dtnp.setSecondlastname("0");
    dtnp.setEmail("Test");
    dtnp.setUserid("Test");
    dtnp.setRoles("administrator");
    when(iCitizenData.saveCitizen(any())).thenReturn(dtc);

    Assert.assertEquals(0, personController.createPerson(dtnp));
  }

  @Test
  public void createPersonTestWhenPersonIsVaccinator() {
    DTCitizen dtc = new DTCitizen();
    DTNewPerson dtnp = new DTNewPerson();
    dtnp.setCi("Test");
    dtnp.setBirthday(new Date());
    dtnp.setIdUruguay("Test");
    dtnp.setSecondlastname("0");
    dtnp.setEmail("Test");
    dtnp.setUserid("Test");
    dtnp.setRoles("vaccinator");
    when(iCitizenData.saveCitizen(any())).thenReturn(dtc);

    Assert.assertEquals(0, personController.createPerson(dtnp));
  }

  @Test
  public void createPersonTestWhenPersonIsAuthority() {
    DTCitizen dtc = new DTCitizen();
    DTNewPerson dtnp = new DTNewPerson();
    dtnp.setCi("Test");
    dtnp.setBirthday(new Date());
    dtnp.setIdUruguay("Test");
    dtnp.setSecondlastname("0");
    dtnp.setEmail("Test");
    dtnp.setUserid("Test");
    dtnp.setRoles("authority");
    when(iCitizenData.saveCitizen(any())).thenReturn(dtc);

    Assert.assertEquals(0, personController.createPerson(dtnp));
  }

  @Test
  public void removeUserLdapTest() throws NamingException {
    personController.removeUserLdap("Test");
  }

  @Test
  public void addUserLdapTest() throws NamingException {
    personController.addUserLdap("Test", "Test");
  }
}
