package backoffice.security;

import static backoffice.util.Utils.*;
import static javax.faces.context.FacesContext.getCurrentInstance;

import DTO.DTRole;
import IController.IPersonController;
import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.session.AdminSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named
@SessionScoped
@Specializes
public class LogOn extends AdminSession implements Serializable {

  private String currentUser;
  private String email;
  private String password;
  private boolean remember;
  private String name;
  private String lastname;
  private String role;


  @Inject
  private AdminConfig adminConfig;

  @Inject
  IPersonController iPersonController;

  public void login() throws IOException {
    if (email != null) {
      try {
        if (iPersonController.authenticateLDAP(email, password) && getUserData()) {
          //Logré la conexión
          currentUser = email;
//          getUserData();
          FacesContext context = getCurrentInstance();
          context.getExternalContext().getSessionMap().put("user", email);
          Faces.getExternalContext().getFlash().setKeepMessages(true);
          Faces.getExternalContext().getSessionMap().put("user", email);
          Faces.redirect(adminConfig.getIndexPage());
        } else if(!getUserData()){
            addDetailMessage("Error: El usuario " + email + " no tiene rol para acceder al sistema'", FacesMessage.SEVERITY_ERROR);
            setPassword(null);
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect(adminConfig.getLoginPage());
          }else{
            //Credenciales inválidas
            addDetailMessage("Error: Credenciales inválidas para el usuario '" + email + "'", FacesMessage.SEVERITY_ERROR);
            setPassword(null);
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect(adminConfig.getLoginPage());
          }
      } catch (Exception e) {
        addDetailMessage("SE HA GENERADO UN ERROR INTERNO, POR FAVOR COMUNÍQUESE CON UN ADMINISTRADOR", FacesMessage.SEVERITY_FATAL);
      }
    }
  }

  public void doLogout() throws InterruptedException, IOException {
    CountDownLatch latch = new CountDownLatch(1);
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    latch.countDown();
    Faces.redirect(adminConfig.getLoginPage());
  }

  public boolean getUserData() throws Exception {
    boolean existInDataBase = false;
    if(iPersonController.getPersonByCI(email) != null){
      existInDataBase = true;
      boolean adm = false;
      boolean aut = false;
      setName(iPersonController.getPersonByCI(email).getName());
      setLastname(iPersonController.getPersonByCI(email).getLastname());
      List<DTRole> dtRoleList = (iPersonController.getPersonByCI(email).getRoles());
      for(DTRole dtRole : dtRoleList){
        if(dtRole.getName().toLowerCase().equals("administrator") && dtRole.getDeleteDate() == null)
          adm = true;
        if(dtRole.getName().toLowerCase().equals("authority") && dtRole.getDeleteDate() == null)
          aut = true;
      }
      if(adm && aut) {
        setRole("Super");
      }else if(adm){
        setRole("Administrador");
      }else{
        setRole("Autoridad");
      }
      return existInDataBase;
    }else
      return  existInDataBase;
  }

  @Override
  public boolean isLoggedIn() {
    return currentUser != null;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isRemember() {
    return remember;
  }

  public void setRemember(boolean remember) {
    this.remember = remember;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
  }



}
