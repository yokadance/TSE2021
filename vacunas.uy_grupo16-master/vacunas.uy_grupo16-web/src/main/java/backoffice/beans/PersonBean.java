package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.*;
import IController.*;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerations.Sex;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named("PersonBean")
@ViewScoped
public class PersonBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String idUruguay;

  private String ci;

  private String name;

  private String lastname;

  private String sex;

  private Date birthday;

  private Date date9;

  private String email;

  //  private String role;

  private DTPerson dtPer;

  private List<DTPerson> listDtPersons;

  //  private List<DTBasicPerson> listDtBasicPersons;

  private List<DTPerson> selectedPersons;

  private List<DTPerson> filteredPersons;

  //  private List<String> listDtRoles;

  Filter<DTVaccine> filter = new Filter<>(new DTVaccine());

  @EJB
  IPersonController iPersonController;

  //  @EJB
  //  IRoleController iRoleController;

  public PersonBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTPerson d = iPersonController.getPerson(id);
      setId(d.getId());
      setIdUruguay(d.getIdUruguay());
      setCi(d.getCi());
      setName(d.getName());
      setLastname(d.getLastname());

      if (d.getSex() == Sex.feminine) setSex("0");
      if (d.getSex() == Sex.masculine) setSex("1");
      if (d.getSex() == Sex.other) setSex("2");

      setBirthday(d.getBirthday());
      setDate9(d.getBirthday());
      setEmail(d.getEmail());
      setListDtPersons(getListDtPersons());
      //      setListDtRoles(getNamesFromRoles());
    } else {
      //      setListDtRoles(getNamesFromRoles());
      DTPerson d = new DTPerson();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public Filter<DTVaccine> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTVaccine> filter) {
    this.filter = filter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdUruguay() {
    return idUruguay;
  }

  public void setIdUruguay(String idUruguay) {
    this.idUruguay = idUruguay;
  }

  public String getCi() {
    return ci;
  }

  public void setCi(String ci) {
    this.ci = ci;
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public Date getDate9() {
    return date9;
  }

  public void setDate9(Date date9) {
    this.date9 = date9;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  //  public String getRole() {
  //    return role;
  //  }

  //  public void setRole(String role) {
  //    this.role = role;
  //  }

  public DTPerson getDtPer() {
    return dtPer;
  }

  public void setDtPer(DTPerson dtPer) {
    this.dtPer = dtPer;
  }

  public List<DTPerson> getListDtPersons() {
    return listDtPersons;
  }

  public void setListDtPersons(List<DTPerson> listDtPersons) {
    this.listDtPersons = listDtPersons;
  }

  //  public List<DTBasicPerson> getListDtBasicPersons() {
  //    return listDtBasicPersons;
  //  }

  //  public void setListDtBasicPersons(List<DTBasicPerson> listDtBasicPerson) {
  //    this.listDtBasicPersons = listDtBasicPerson;
  //  }

  public List<DTPerson> getSelectedPersons() {
    return selectedPersons;
  }

  public void setSelectedPersons(List<DTPerson> selectedPersons) {
    this.selectedPersons = selectedPersons;
  }

  public List<DTPerson> getFilteredPersons() {
    return filteredPersons;
  }

  public void setFilteredPersons(List<DTPerson> filteredPersons) {
    this.filteredPersons = filteredPersons;
  }

  //  public List<String> getListDtRoles() {
  //    return listDtRoles;
  //  }

  //  public void setListDtRoles(List<String> listDtRoles) {
  //    this.listDtRoles = listDtRoles;
  //  }

  //  public List<String> getNamesFromRoles(){
  //    List<DTRole> roleList = iRoleController.getRoles();
  //    List<String> roleNames = new ArrayList<>();
  //    for(DTRole dtRole : roleList){
  //      roleNames.add(dtRole.getName());
  //    }
  //    return roleNames;
  //  }

  public void onDateSelect(SelectEvent<Date> event) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
  }

  public void click() {
    PrimeFaces.current().ajax().update("form:display");
    PrimeFaces.current().executeScript("PF('dlg').show()");
  }

  public void save() throws IOException {
    String msg;
    Sex sexo = Sex.other;
    if (Integer.parseInt(getSex()) == 0) sexo = Sex.feminine;
    if (Integer.parseInt(getSex()) == 1) sexo = Sex.masculine;

    DTPerson d = new DTPerson(getIdUruguay(), getCi(), getName(), getLastname(), sexo, getDate9(), getEmail());

    if (getId() == null) {
      iPersonController.savePerson(d);
      msg = "Persona " + d.getCi() + " creada";
    } else {
      d.setId(getId());
      iPersonController.savePerson(d);
      msg = "Persona " + d.getCi() + " actualizada";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("person.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtPer == null || dtPer.getId() == null;
  }

  public void clear() {
    id = null;
    idUruguay = null;
    ci = null;
    name = null;
    lastname = null;
    sex = null;
    birthday = null;
    date9 = null;
    email = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iPersonController.deletePerson(id);
      DTPerson d = iPersonController.getPerson(id);
      addDetailMessage("Persona " + d.getCi() + " eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("person.jsf");
    }
  }

  private void catchData() {
    try {
      listDtPersons = iPersonController.getPersons();
      //      listDtBasicPersons = iPersonController.getBasicPersons();

    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addPerson(DTPerson newPer) {
    try {
      iPersonController.savePerson(newPer);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remPersons() throws IOException {
    try {
      for (DTPerson l : selectedPersons) {
        iPersonController.deletePerson(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedPersons.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("person.jsf");
    return "ok";
  }

  public String updPerson(DTPerson updPer) {
    try {
      iPersonController.savePerson(updPer);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTPerson> getPersons() {
    return listDtPersons;
  }
  //  public List<DTBasicPerson> getBasicPersons() {
  //    return listDtBasicPersons;
  //  }

  //  public DTVaccine getVaccineById(Long id) {
  //    try {
  //      return iVaccineController.getVaccineById(id);
  //    } catch (Exception e) {
  //      return new DTVaccine();
  //    }
  //  }

}
