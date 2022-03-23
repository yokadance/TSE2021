package IDAL;

import DTO.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPersonData {
  void savePerson(DTPerson dtp);
  int createPersonCitizen(DTPerson dtp, DTCitizen dtCitizen);
  int createPersonAdministrator(DTPerson dtp, DTAdministrator dtAdministrator);
  int createPersonVaccinator(DTPerson dtp, DTVaccinator dtVaccinator);
  int createPersonAuthority(DTPerson dtp, DTAuthority dtAuthority);
  void deletePerson(Long id);
  DTPerson getByIdPerson(Long id);
  List<DTPerson> getPersons();
  List<DTBasicPerson> getBasicPersons();
  DTPerson getPersonByCI(String ci) throws Exception;
  boolean existPersonByCI(String ci) throws Exception;
  List<String> getUserRoles(String ci);
  List<DTRole> getDtRolesFromUser(String ci);
  //boolean existPerson(String ci);
  //DTPerson getPersonfromCI(String ci) throws Throwable;
}
