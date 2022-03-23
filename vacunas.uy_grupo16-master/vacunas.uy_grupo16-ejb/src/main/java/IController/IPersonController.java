package IController;

import DTO.DTBasicPerson;
import DTO.DTNewPerson;
import DTO.DTPerson;
import DTO.DTRole;

import javax.ejb.Local;
import javax.naming.NamingException;
import java.util.List;

@Local
public interface IPersonController {
  void savePerson(DTPerson dtPerson);
  int createPerson(DTNewPerson dtNewPerson);
  DTPerson getPerson(Long id);
  List<DTPerson> getPersons();
  List<DTBasicPerson> getBasicPersons();
  void deletePerson(Long id);
  boolean authenticateLDAP(String username, String password) throws Exception;
  void addUserLdap(String usuario, String password) throws NamingException;
  String getUserDN(final String userName);
  void removeUserLdap(String usuario) throws NamingException;
  boolean existsLdapUser(String user) throws NamingException;
  DTPerson getPersonByCI(String ci) throws Exception;
  boolean existPersonByCI(String ci) throws Exception;
  List<String> getUserRoles(String ci) throws Exception;
  List<DTRole> getDtRolesFromUser(String ci);
}
