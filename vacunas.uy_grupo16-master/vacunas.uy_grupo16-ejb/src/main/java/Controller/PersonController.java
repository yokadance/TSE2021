package Controller;

import DTO.*;
import IController.IPersonController;
import IDAL.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;



@Stateless
@LocalBean

public class PersonController implements IPersonController {

  @EJB
  IPersonData iPersonData;

  @EJB
  ICitizenData iCitizenData;

  @EJB
  IAdministratorData iAdministratorData;

  @EJB
  IAuthorityData iAuthorityData;

  @EJB
  IVaccinatorData iVaccinatorData;

  DirContext dctx = null;

  public void savePerson(DTPerson dtPerson) {
    iPersonData.savePerson(dtPerson);
  } //testDone



  @Override
  public int createPerson(DTNewPerson dtNewPerson) {
   // List<DTRole> dtRoleList = new ArrayList<>();
    DTPerson dtPerson = new DTPerson();
    dtPerson.setCi(dtNewPerson.getCi());
    dtPerson.setBirthday(dtNewPerson.getBirthday());
    dtPerson.setIdUruguay(dtNewPerson.getIdUruguay());
    dtPerson.setSex(dtNewPerson.getSex());
    dtPerson.setEmail(dtNewPerson.getEmail());
    dtPerson.setUserid(dtNewPerson.getUserid());
    if (dtNewPerson.getRoles().equals("citizen")) {
      DTCitizen dtCitizen = iCitizenData.saveCitizen(new DTCitizen());
      return iPersonData.createPersonCitizen(dtPerson, dtCitizen);
    }
    if (dtNewPerson.getRoles().equals("administrator")) {
      DTAdministrator dtAdministrator = iAdministratorData.saveAdministrator(new DTAdministrator());
      return iPersonData.createPersonAdministrator(dtPerson, dtAdministrator);
    }
    if (dtNewPerson.getRoles().equals("vaccinator")) {
      DTVaccinator dtVaccinator = iVaccinatorData.saveVaccinator(new DTVaccinator());
      return iPersonData.createPersonVaccinator(dtPerson, dtVaccinator);
    }
    if (dtNewPerson.getRoles().equals("authority")) {
      DTAuthority dtAuthority = iAuthorityData.saveAuthority(new DTAuthority());
      return iPersonData.createPersonAuthority(dtPerson, dtAuthority);
    }
    return 0;
  }

  public String getUserDN(final String userName) {
    //String userDN = new StringBuffer().append("cn=").append(userName).append(",OU=People,dc=vacunas,dc=uy").toString();
    return  new StringBuffer().append("cn=").append(userName).append(",OU=People,dc=vacunas,dc=uy").toString();
  } //testDone

  public boolean existsLdapUser(String user) throws NamingException {
    String userToDelete = this.getUserDN(user);

    try {
      final Hashtable<Object, Object> env = new Hashtable<Object, Object>();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
      //props.put(Context.PROVIDER_URL, "ldap://192.168.1.3:389");
      env.put(Context.PROVIDER_URL, "ldap://179.31.2.95:389");
      env.put(Context.SECURITY_AUTHENTICATION, "simple");
      env.put(Context.SECURITY_PRINCIPAL, "cn=ldapadm,dc=vacunas,dc=uy"); //adminuser - User with special priviledge, dn user
      env.put(Context.SECURITY_CREDENTIALS, "root"); //dn user password

      DirContext dctx = null;
      dctx = new InitialDirContext(env);

      if (dctx.getAttributes(userToDelete) != null) {
        return true;
      }

      return false;
    } catch (final NameNotFoundException e) {
      return false;
    }
  }

  public void removeUserLdap(String usuario) throws NamingException {
    final Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    DirContext dctx = null;
    try {
      String url = "ldap://179.31.2.95:389";
      String conntype = "simple";
      String AdminDn = "cn=ldapadm,dc=vacunas,dc=uy";
      String passwordConnection = "root";

      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
      env.put(Context.PROVIDER_URL, url);
      env.put(Context.SECURITY_AUTHENTICATION, conntype);
      env.put(Context.SECURITY_PRINCIPAL, AdminDn);
      env.put(Context.SECURITY_CREDENTIALS, passwordConnection);
      dctx = new InitialDirContext(env);

      String delete = getUserDN(usuario);
      if (delete != null) {
        try {
          dctx.destroySubcontext(getUserDN(usuario));
        } catch (NamingException e) {
          e.printStackTrace();
        }
      }
    } finally {
      if (null != dctx) {
        try {
          dctx.close();
        } catch (final NamingException e) {
          System.out.println("Error in closing ldap " + e);
        }
      }
    }
  }

  public void addUserLdap(String usuario, String password) throws NamingException {
    final Hashtable<Object, Object> env = new Hashtable<Object, Object>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldap://179.31.2.95:389");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "cn=ldapadm,dc=vacunas,dc=uy"); //adminuser - User with special priviledge, dn user
    env.put(Context.SECURITY_CREDENTIALS, "root"); //dn user password

    DirContext dctx = null;
    dctx = new InitialDirContext(env);

    try {
      String url = "ldap://179.31.2.95:389";
      String conntype = "simple";
      String AdminDn = "cn=ldapadm,dc=vacunas,dc=uy";
      String passwordConnection = "root";

      final Attributes container = new BasicAttributes();

      final Attribute objClasses = new BasicAttribute("objectClass");
      objClasses.add("inetOrgPerson");

      final Attribute commonName = new BasicAttribute("cn", usuario);
      final Attribute uid = new BasicAttribute("uid", usuario);
      final Attribute surName = new BasicAttribute("sn", "test2");

      final Attribute userPassword = new BasicAttribute("userPassword", password);

      container.put(objClasses);
      container.put(commonName);
      container.put(uid);
      container.put(surName);
      container.put(userPassword);

      //dctx.createSubcontext(getUserDN(usuario), container);

      dctx.createSubcontext(getUserDN(usuario), container);

    } finally {
      if (null != dctx) {
        Logger logger = null;
        try {
          dctx.close();
        } catch (final NamingException e) {
          //System.out.println("Error in closing ldap " + e);

        }
      }
    }
  }

  public boolean authenticateLDAP(String username, String password) throws Exception {
    Properties props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    props.put(Context.PROVIDER_URL, "ldap://179.31.2.95:389");
    props.put(Context.SECURITY_AUTHENTICATION, "simple");
    props.put(Context.SECURITY_PRINCIPAL, "cn=ldapadm,dc=vacunas,dc=uy"); //adminuser - User with special priviledge, dn user
    props.put(Context.SECURITY_CREDENTIALS, "root"); //dn user password

    InitialDirContext context = new InitialDirContext(props);

    try {
      props = new Properties();
      props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
      props.put(Context.PROVIDER_URL, "ldap://179.31.2.95:389");
      //props.put(Context.SECURITY_PRINCIPAL,"cn="+ username +",ou=People,dc=vacunas,dc=uy username");
      props.put(Context.SECURITY_PRINCIPAL, getUserDN(username));
      props.put(Context.SECURITY_CREDENTIALS, password);

      context = new InitialDirContext(props);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public DTPerson getPerson(Long id) {
    return iPersonData.getByIdPerson(id);
  }

  public List<DTPerson> getPersons() {
    return iPersonData.getPersons();
  }

  public List<DTBasicPerson> getBasicPersons() {
    return iPersonData.getBasicPersons();
  }

  public void deletePerson(Long id) {
    iPersonData.deletePerson(id);
  }

  public DTPerson getPersonByCI(String ci) throws Exception {
    if (ci == null || ci.isEmpty()) throw new Exception("CI vacía");

    return iPersonData.getPersonByCI(ci);
  }

  public boolean existPersonByCI(String ci) throws Exception {
    if (ci == null || ci.isEmpty()) throw new Exception("CI vacía");
    return iPersonData.existPersonByCI(ci);
  }

  public List<String> getUserRoles(String ci) throws Exception {
    if (ci == null || ci.isEmpty()) throw new Exception("CI vacía");
    return iPersonData.getUserRoles(ci);
  }

  public List<DTRole> getDtRolesFromUser(String ci) {
    return iPersonData.getDtRolesFromUser(ci);
  }

  public void setiPersonData(IPersonData iPersonData) {
    this.iPersonData = iPersonData;
  }

  public void setiCitizenData(ICitizenData iCitizenData) {
    this.iCitizenData = iCitizenData;
  }

  public void setiAdministratorData(IAdministratorData iAdministratorData) {
    this.iAdministratorData = iAdministratorData;
  }

  public void setiAuthorityData(IAuthorityData iAuthorityData) {
    this.iAuthorityData = iAuthorityData;
  }

  public void setiVaccinatorData(IVaccinatorData iVaccinatorData) {
    this.iVaccinatorData = iVaccinatorData;
  }

  public void setDctx(DirContext dctx) {
    this.dctx = dctx;
  }
}
