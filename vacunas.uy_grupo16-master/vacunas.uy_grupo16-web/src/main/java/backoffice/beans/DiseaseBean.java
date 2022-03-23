package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.DTDisease;
import DTO.DTVaccine;
import IController.IDiseaseController;
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

@Named("DiseaseBean")
@ViewScoped
public class DiseaseBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String name;

  private String symptom;

  private DTDisease dtDis;

  private List<DTDisease> listDtDis;

  private List<DTDisease> selectedDiseases;

  private List<DTDisease> filteredDiseases;

  private Long disId;

  Filter<DTDisease> filter = new Filter<>(new DTDisease());

  @EJB
  IDiseaseController iDiseaseController;

  public DiseaseBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTDisease d = iDiseaseController.getDiseaseById(id);
      setName(d.getName());
      setSymptom(d.getSymptons());
    } else {
      DTDisease d = new DTDisease();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public List<DTDisease> getSelectedDiseases() {
    return selectedDiseases;
  }

  public void setSelectedDiseases(List<DTDisease> selectedDiseases) {
    this.selectedDiseases = selectedDiseases;
  }

  public List<DTDisease> getFilteredDiseases() {
    return filteredDiseases;
  }

  public void setFilteredDiseases(List<DTDisease> filteredDiseases) {
    this.filteredDiseases = filteredDiseases;
  }

  public Filter<DTDisease> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTDisease> filter) {
    this.filter = filter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSymptom() {
    return symptom;
  }

  public void setSymptom(String symptom) {
    this.symptom = symptom;
  }

  public List<DTDisease> getListDtDis() {
    return listDtDis;
  }

  public void setListDtDis(List<DTDisease> listDtDis) {
    this.listDtDis = listDtDis;
  }

  public void save() throws IOException {
    String msg;
    if(iDiseaseController.checkDiseaseNameAvailability(getName())) {
      DTDisease d = new DTDisease(getName(), getSymptom());
      if (getId() == null) {
        iDiseaseController.saveDisease(d);
        msg = "Enfermedad " + d.getName() + " creado";
      } else {
        d.setId(getId());
        iDiseaseController.saveDisease(d);
        msg = "Enfermedad " + d.getName() + " actualizada";
      }
    }else {
      msg = "El nombre: " + getName() + " ya existe";
    }
    addDetailMessage(msg);
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("disease.jsf");
  }

  public boolean isNew() {
    Long i = getId();
    return dtDis == null || dtDis.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    symptom = null;
  }

  public void remove() throws IOException {
    if (has(id)) {
      iDiseaseController.deleteDisease(id);
      DTDisease d = iDiseaseController.getDiseaseById(id);
      addDetailMessage("Enfermedad " + d.getName() + " eliminado");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("disease.jsf");
    }
  }

  private void catchData() {
    try {
      listDtDis = iDiseaseController.listDiseases();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addDisease(DTDisease newDis) {
    try {
      iDiseaseController.saveDisease(newDis);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué devolver
  }

  public String remDiseases() throws IOException {
    try {
      for (DTDisease l : selectedDiseases) {
        iDiseaseController.deleteDisease(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedDiseases.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("disease.jsf");
    return "ok";
  }

  public String updDisease(DTDisease updDis) {
    try {
      iDiseaseController.saveDisease(updDis);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTDisease> getDiseases() {
    return listDtDis;
  }

  public DTDisease getDiseaseById(Long id) {
    try {
      return iDiseaseController.getDiseaseById(id);
    } catch (Exception e) {
      return new DTDisease();
    }
  }
  //    public void getDiseaseByName(String name) {

  //        if(!name.isEmpty()) {
  //            try {
  //                filteredDiseases = new ArrayList<>();
  //                filteredDiseases = iDiseaseController.getDiseaseByName(name);
  //            } catch (Exception e) {
  //                filteredDiseases = new ArrayList<>();
  //            }
  //        }else{
  ////            filteredDiseases = new ArrayList<>();
  ////            listDtDis = iDiseaseController.listDiseases();
  //            catchData();
  //        }
  //    }

}
