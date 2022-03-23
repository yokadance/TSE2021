package backoffice.beans;

import DTO.DTPerson;
import IController.IPersonController;
import IController.IRoleController;
import entities.Role;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean {

  private String username;
  private String password;
  private String role;
  private DTPerson dtPerson;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @EJB
  IPersonController iPersonController;

  IRoleController iRoleController;

  private UIComponent component;

  public UIComponent getComponent() {
    return component;
  }

  public void setComponent(UIComponent component) {
    this.component = component;
  }

  public String login(String user, String password) throws Exception {
    if (username != null) {
      if (iPersonController.authenticateLDAP(user, password)) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", username);
        return "index";
      } else return null;
    } else return null;
  }
}
