package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.DTLaboratory;
import IController.ILaboratoryController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Faces;

@Named("LaboratoryBean")
@ViewScoped
public class LaboratoryBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;
  private String name;
  private String origin;
  private String email;
  private String phone;

  private DTLaboratory dtLab;

  private List<DTLaboratory> listDtLab;

  private List<DTLaboratory> selectedLaboratories;

  private List<DTLaboratory> filteredLaboratories;

  private Long labId;

  Filter<DTLaboratory> filter = new Filter<>(new DTLaboratory());

  @EJB
  ILaboratoryController iLaboratoryController;

  public LaboratoryBean() {
  }

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTLaboratory d = iLaboratoryController.getLaboratoryById(id);
      setName(d.getName());
      setOrigin(d.getOrigin());
      setEmail(d.getEmail());
      setPhone(d.getPhone());
    } else {
      DTLaboratory d = new DTLaboratory();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public List<DTLaboratory> getSelectedLaboratories() {
    return selectedLaboratories;
  }

  public void setSelectedLaboratories(List<DTLaboratory> selectedLaboratories) {
    this.selectedLaboratories = selectedLaboratories;
  }

  public List<DTLaboratory> getFilteredLaboratories() {
    return filteredLaboratories;
  }

  public void setFilteredLaboratories(List<DTLaboratory> filteredLaboratories) {
    this.filteredLaboratories = filteredLaboratories;
  }

  public Filter<DTLaboratory> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTLaboratory> filter) {
    this.filter = filter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<DTLaboratory> getListDtLab() {
    return listDtLab;
  }

  public void setListDtLab(List<DTLaboratory> listDtLab) {
    this.listDtLab = listDtLab;
  }

  public void save() throws IOException {
    String msg;
    if(iLaboratoryController.checkLaboratoryNameAvailability(getName())) {
      DTLaboratory d = new DTLaboratory(getName(), getOrigin(), getEmail(), getPhone());
      if (getId() == null) {
        iLaboratoryController.saveLaboratory(d);
        msg = "Laboratorio " + d.getName() + " creado";
      } else {
        d.setId(getId());
        iLaboratoryController.saveLaboratory(d);
        msg = "Laboratorio " + d.getName() + " actualizado";
      }
    }else{
      msg = "El nombre: " + getName() + " ya existe";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("laboratory.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtLab == null || dtLab.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    origin = null;
    email = null;
    phone = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iLaboratoryController.deleteLaboratory(id);
      DTLaboratory d = iLaboratoryController.getLaboratoryById(id);
      addDetailMessage("Laboratorio " + d.getName() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("laboratory.jsf");
    }
  }

  private void catchData() {
    try {
      listDtLab = iLaboratoryController.listLaboratories();
    } catch (Exception e) {
    }
  }

  public String addLaboratory(DTLaboratory newLab) {
    try {
      iLaboratoryController.saveLaboratory(newLab);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué devolver
  }

  public String remLaboratories() throws IOException {
    try {
      for (DTLaboratory l : selectedLaboratories) {
        iLaboratoryController.deleteLaboratory(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedLaboratories.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("laboratory.jsf");
    return "ok";
  }

  public String updLaboratory(DTLaboratory updLab) {
    try {
      iLaboratoryController.saveLaboratory(updLab);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTLaboratory> getLaboratories() {
    return listDtLab;
  }

  public DTLaboratory getLaboratoriesById(Long id) {
    try {
      return iLaboratoryController.getLaboratoryById(id);
    } catch (Exception e) {
      return new DTLaboratory();
    }
  }

}