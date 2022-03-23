package backoffice.beans;

import DTO.*;
import IController.*;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Citizen;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("RoleBean")
@SessionScoped
public class RoleBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long personId;

  private Long id;

  private String role;

  private DTRole dtRol;

  private String nameTitle;

  private Boolean hideDiv;

  private List<DTRole> listDTRoles;

  private List<DTRole> selectedRoles;

  private List<DTRole> filteredRoles;

//  Filter<DTRole> filter = new Filter<>(new DTRole());

  @EJB
  IRoleController iRoleController;

  @EJB
  IPersonController iPersonController;

  @EJB
  ICitizenController iCitizenController;

  @EJB
  IAuthorityController iAuthorityController;

  @EJB
  IAdministratorController iAdministratorController;

  @EJB
  IVaccinatorController iVaccinatorController;

  public RoleBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTCitizen d = iCitizenController.getByIdCitizen(id);
      setRole(d.getName());
      setHideDiv(false);
    } else {
      DTCitizen d = new DTCitizen();
      setPersonId(personId);
      setHideDiv(true);
      catchData();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }


  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public DTRole getDtRol() {
    return dtRol;
  }

  public void setDtRol(DTRole dtRol) {
    this.dtRol = dtRol;
  }

  public Boolean getHideDiv() {
    return hideDiv;
  }

  public void setHideDiv(Boolean hideDiv) {
    this.hideDiv = hideDiv;
  }

  public List<DTRole> getListDTRoles() {
    return listDTRoles;
  }

  public void setListDTRoles(List<DTRole> listDTRoles) {
    this.listDTRoles = listDTRoles;
  }

  public List<DTRole> getSelectedRoles() {
    return selectedRoles;
  }

  public void setSelectedRoles(List<DTRole> selectedRoles) {
    this.selectedRoles = selectedRoles;
  }

  public List<DTRole> getFilteredRoles() {
    return filteredRoles;
  }

  public void setFilteredRoles(List<DTRole> filteredRoles) {
    this.filteredRoles = filteredRoles;
  }

  public String test(){
    Long pId = getPersonId();
    clear();
    return "role?faces-redirect=true?includeViewParams=true&personId="+pId;
  }


  public void save() throws IOException, ParseException, NamingException {
    String msg;
    DTPerson person = iPersonController.getPerson(personId);
    String ci = person.getCi();

    if(role != null){
      String optSave = role.toLowerCase();
      switch(optSave) {
        case "ciudadano":

          DTCitizen dtC = new DTCitizen();
          dtC.setDtPerson(person);

          if (getId() == null) {
            iRoleController.saveAnyRole(dtC);
            msg = "Rol Ciudadano creado";
          } else {
            dtC.setId(getId());
            iRoleController.saveAnyRole(dtC);
            msg = "Rol Ciudadano actualizado";
          }
            break;

        case "autoridad":
          Boolean teztA = iPersonController.existsLdapUser(ci);
          if(!iPersonController.existsLdapUser(ci)){
            iPersonController.addUserLdap(ci, ci);
          }
          DTAuthority dtA = new DTAuthority();
          dtA.setDtPerson(person);

          if (getId() == null) {
            iRoleController.saveAnyRole(dtA);
            msg = "Rol Autoridad creado";
          } else {
            dtA.setId(getId());
            iRoleController.saveAnyRole(dtA);
            msg = "Rol Autoridad creado";
          }
          break;

        case "administrador":
          Boolean teztB = iPersonController.existsLdapUser(ci);
          if(!iPersonController.existsLdapUser(ci)){
            iPersonController.addUserLdap(ci, ci);
          }
          DTAdministrator dtM = new DTAdministrator();
          dtM.setDtPerson(person);

          if (getId() == null) {
            iRoleController.saveAnyRole(dtM);
            msg = "Rol Administrador actualizado";
          } else {
            dtM.setId(getId());
            iRoleController.saveAnyRole(dtM);
            msg = "Rol Administrador actualizado";
          }
          break;

        case "vacunador":

          DTVaccinator dtV = new DTVaccinator();
          dtV.setDtPerson(person);

          if (getId() == null) {
            iRoleController.saveAnyRole(dtV);
            msg = "Rol Vacunador creado";
          } else {
            dtV.setId(getId());
            iRoleController.saveAnyRole(dtV);
            msg = "Rol Vacunador actualizado";
          }
          break;

        default: msg = "Rol " + optSave + "  no existe";
          break;

      }
      clear();
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("role.jsf?personId="+personId);
    }else{
      msg = "Seleccione un Rol";
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
    }

  }

  public boolean isNew() {
    Long i = getId();
    return dtRol == null || dtRol.getId() == null;
  }

  public void clear() {
    id = null;
    role = null;
  }

  public void remove() throws IOException, NamingException {

    if (has(id)) {
      String optRem = role.toLowerCase();
      DTPerson dtPerson = iPersonController.getPerson(personId);
      switch(optRem) {
        case "ciudadano":
          iCitizenController.deleteCitizen(id);
          addDetailMessage("Role Ciudadano eliminado");
          break;
        case "autoridad":
          iAuthorityController.deleteAuthority(id);
          addDetailMessage("Role Autoridad eliminado");
          break;
        case "administrador":
          iAdministratorController.deleteAdministrator(id);
          addDetailMessage("Role Administrador eliminado");
          break;
        case "vacunador":
          iVaccinatorController.deleteVaccinator(id);
          addDetailMessage("Role Vacunador eliminado");
          break;
      }
      Boolean adm = false;
      Boolean aut = false;
      List<DTRole> listDtRoles = dtPerson.getRoles();
      for(DTRole dtR : listDtRoles){
        if(dtR.getName().toLowerCase().equals("administrator") && dtR.getDeleteDate() == null)
          adm = true;
        if(dtR.getName().toLowerCase().equals("authority") && dtR.getDeleteDate() == null)
          aut = true;
      }

      if(!adm && !aut && iPersonController.existsLdapUser(dtPerson.getCi())){
        iPersonController.removeUserLdap(dtPerson.getCi());
      }
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("role.jsf?personId="+personId);

    }
  }

  private void catchData() {
    try {
      Long i = getPersonId();
      String ci = iPersonController.getPerson(i).getCi();
      listDTRoles = iPersonController.getDtRolesFromUser(ci);
      setNameTitle(ci);
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addRole(DTRole newRol) {
    try {
      DTCitizen dtCit = (DTCitizen) newRol;
      iRoleController.saveRole(dtCit);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remRoles() throws IOException, NamingException {
    try {
      for (DTRole l : selectedRoles) {
        iRoleController.deleteRole(l);
      }
    } catch (Exception e) {
      return e.getMessage();
    }
    DTPerson dtPerson = iPersonController.getPerson(personId);
    Boolean adm = false;
    Boolean aut = false;
    List<DTRole> listDtRoles = dtPerson.getRoles();
    for(DTRole dtR : listDtRoles){
      if(dtR.getName().toLowerCase().equals("administrator") && dtR.getDeleteDate() == null)
        adm = true;
      if(dtR.getName().toLowerCase().equals("authority") && dtR.getDeleteDate() == null)
        aut = true;
    }

    if(!adm && !aut && iPersonController.existsLdapUser(dtPerson.getCi())){
      iPersonController.removeUserLdap(dtPerson.getCi());
    }

    selectedRoles.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("role.jsf?personId"+personId);
    return "ok";
  }

  public String updRole(DTRole updRol) {
    try {
      DTCitizen dtCit = (DTCitizen) updRol;
      iCitizenController.saveCitizen(dtCit);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTRole > getRoles() {
    return listDTRoles;
  }


  public String getNameTitle() {
    return nameTitle;
  }

  public void setNameTitle(String nameTitle) {
    this.nameTitle = iPersonController.getPerson(personId).getName() + " " + iPersonController.getPerson(personId).getLastname();
  }

//  public String nameTitle(){
//    return iPersonController.getPerson(personId).getName() + " " + iPersonController.getPerson(personId).getLastname();
//  }

}
