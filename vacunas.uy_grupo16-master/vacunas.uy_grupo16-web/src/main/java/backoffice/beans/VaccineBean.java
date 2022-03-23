package backoffice.beans;

import DTO.DTDisease;
import DTO.DTLaboratory;
import DTO.DTVaccine;
import IController.IDiseaseController;
import IController.ILaboratoryController;
import IController.IVaccineController;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

@Named("VaccineBean")
@ViewScoped
public class VaccineBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String name;

  private int doseQuantity;

  private float temperature;

  private int monthQuantity;

  private int inmunity;

  private String disease;

  private String laboratory;

  private DTVaccine dtVacc;

  private List<DTVaccine> listDtVaccines;

  private List<DTVaccine> selectedVaccines;

  private List<DTVaccine> filteredVaccines;

  private List<String> listDtDiseases;

  private List<String> listDtLaboratories;


  private List<DTDisease> dtDiseaseList;
  private DTDisease dtDiseaseSelected;
  private List<DTLaboratory> dtLaboratoyList;
  private DTLaboratory dtLaboratorySelected;


  private Long vaccId;

  Filter<DTVaccine> filter = new Filter<>(new DTVaccine());

  @EJB
  IVaccineController iVaccineController;
  @EJB
  IDiseaseController iDiseaseController;
  @EJB
  ILaboratoryController iLaboratoryController;

  public VaccineBean() {}

  public void init() {
    if (Faces.isAjaxRequest()) {
      return;
    }
    if (has(id)) {
      DTVaccine d = iVaccineController.getVaccineById(id);
      DTDisease dtDisease = iDiseaseController.getDiseaseById(Long.parseLong(d.getDisease()));
      DTLaboratory dtLaboratory = iLaboratoryController.getLaboratoryById(Long.parseLong(d.getLaboratory()));

      setName(d.getName());
      setDoseQuantity(d.getDoseQuantity());
      setTemperature(d.getTemperature());
      setMonthQuantity(d.getMonthQuantity());
      setInmunity(d.getInmunity());
      setDisease(dtDisease.getName());
      setLaboratory(dtLaboratory.getName());

      setListDtDiseases(getNamesFromDiseases());
      setListDtLaboratories(getNamesFromLaboratories());

      setDtLaboratoyList(iLaboratoryController.listLaboratories());

    } else {
      setListDtDiseases(getNamesFromDiseases());
      setListDtLaboratories(getNamesFromLaboratories());

      setDtDiseaseList(iDiseaseController.listDiseases());
      setDtLaboratoyList(iLaboratoryController.listLaboratories());

      DTVaccine d = new DTVaccine();
    }
  }

  @PostConstruct
  public void initData() {
    catchData();
  }

  public List<DTVaccine> getSelectedVaccines() {
    return selectedVaccines;
  }

  public void setSelectedVaccines(List<DTVaccine> selectedVaccines) {
    this.selectedVaccines = selectedVaccines;
  }

  public List<DTVaccine> getFilteredVaccines() {
    return filteredVaccines;
  }

  public void setFilteredVaccines(List<DTVaccine> filteredVaccines) {
    this.filteredVaccines = filteredVaccines;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDoseQuantity() {
    return doseQuantity;
  }

  public void setDoseQuantity(int doseQuantity) {
    this.doseQuantity = doseQuantity;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

  public int getMonthQuantity() {
    return monthQuantity;
  }

  public void setMonthQuantity(int monthQuantity) {
    this.monthQuantity = monthQuantity;
  }

  public int getInmunity() {
    return inmunity;
  }

  public void setInmunity(int inmunity) {
    this.inmunity = inmunity;
  }

  public String getDisease() {
    return disease;
  }

  public void setDisease(String disease) {
    this.disease = disease;
  }

  public String getLaboratory() {
    return laboratory;
  }

  public void setLaboratory(String laboratory) {
    this.laboratory = laboratory;
  }

  public List<DTVaccine> getListDtVaccines() {
    return listDtVaccines;
  }

  public void setListDtVaccines(List<DTVaccine> listDtVaccines) {
    this.listDtVaccines = listDtVaccines;
  }

  public List<String> getListDtDiseases() {
    return listDtDiseases;
  }

  public void setListDtDiseases(List<String> listDtDiseases) {
    this.listDtDiseases = listDtDiseases;
  }

  public List<String> getListDtLaboratories() {
    return listDtLaboratories;
  }

  public void setListDtLaboratories(List<String> listDtLaboratories) {
    this.listDtLaboratories = listDtLaboratories;
  }

  public List<DTDisease> getDtDiseaseList() {
    return dtDiseaseList;
  }

  public void setDtDiseaseList(List<DTDisease> dtDiseaseList) {
    this.dtDiseaseList = dtDiseaseList;
  }

  public DTDisease getDtDiseaseSelected() {
    return dtDiseaseSelected;
  }

  public void setDtDiseaseSelected(DTDisease dtDiseaseSelected) {
    this.dtDiseaseSelected = dtDiseaseSelected;
  }

  public List<DTLaboratory> getDtLaboratoyList() {
    return dtLaboratoyList;
  }

  public void setDtLaboratoyList(List<DTLaboratory> dtLaboratoyList) {
    this.dtLaboratoyList = dtLaboratoyList;
  }

  public DTLaboratory getDtLaboratorySelected() {
    return dtLaboratorySelected;
  }

  public void setDtLaboratorySelected(DTLaboratory dtLaboratorySelected) {
    this.dtLaboratorySelected = dtLaboratorySelected;
  }

  public List<String> getNamesFromLaboratories(){
    List<DTLaboratory> labList = iLaboratoryController.listLaboratories();
    List<String> labNames = new ArrayList<>();
    for(DTLaboratory dtLab : labList){
      labNames.add(dtLab.getName());
    }
    return labNames;
  }

  public List<String> getNamesFromDiseases(){
    List<DTDisease> disList = iDiseaseController.listDiseases();
    List<String> disNames = new ArrayList<>();
    for(DTDisease dtDis : disList){
      disNames.add(dtDis.getName());
    }
    return disNames;
  }


  public void save() throws IOException {
    String msg;

    if(doseQuantity != 0 && temperature != 0 && monthQuantity != 0 && inmunity != 0) {
      DTVaccine d = new DTVaccine(getName(), getDoseQuantity(), getTemperature(), getMonthQuantity(), getInmunity());
      Long labId = iLaboratoryController.getLaboratoryIdByName(laboratory);
      Long disId = iDiseaseController.getDiseaseIdByName(disease);

//      Long labId = dtLaboratorySelected.getId();
//      Long disId = dtDiseaseSelected.getId();

      if (getId() == null) {
        Long vacId = iVaccineController.saveVaccine(d);
        iVaccineController.addDiseaseToVaccine(vacId, iDiseaseController.getDiseaseById(disId));
        iVaccineController.addLaboratoryToVaccine(vacId, iLaboratoryController.getLaboratoryById(labId));
        msg = "Vacuna " + d.getName() + " creada";
      } else {
        d.setId(getId());
        iVaccineController.saveVaccine(d);
        iVaccineController.addDiseaseToVaccine(getId(), iDiseaseController.getDiseaseById(disId));
        iVaccineController.addLaboratoryToVaccine(getId(), iLaboratoryController.getLaboratoryById(labId));
        msg = "Vacuna " + d.getName() + " actualizada";
      }
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccine.jsf");
    }else{
      msg = "Los valores deben ser mayores a cero";
      addDetailMessage(msg);
      Faces.getFlash().setKeepMessages(true);
    }
  }

  public boolean isNew() {
    Long i = getId();
    return dtVacc == null || dtVacc.getId() == null;
  }

  public void clear() {
    id = null;
    name = null;
    doseQuantity = 0;
    temperature = 0;
    monthQuantity = 0;

  }

  public void remove() throws IOException {
    if (has(id)) {
      iVaccineController.deleteVaccine(id);
      DTVaccine d = iVaccineController.getVaccineById(id);
      addDetailMessage("Vacuna " + d.getName() + " eliminada");
      Faces.getFlash().setKeepMessages(true);
      Faces.redirect("vaccine.jsf");
    }
  }

  private void catchData() {
    try {
      listDtVaccines = iVaccineController.listVaccines();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public String addVaccine(DTVaccine newVacc) {
    try {
      iVaccineController.saveVaccine(newVacc);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok"; //Analizar qué revolver
  }

  public String remVaccines() throws IOException {
    try {
      for (DTVaccine l : selectedVaccines) {
        iVaccineController.deleteVaccine(l.getId());
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    selectedVaccines.clear();
    Faces.getFlash().setKeepMessages(true);
    Faces.redirect("vaccine.jsf");
    return "ok";
  }

  public String updVaccine(DTVaccine updVacc) {
    try {
      iVaccineController.saveVaccine(updVacc);
    } catch (Exception e) {
      return e.getMessage();
    }

    return "ok";
  }

  public List<DTVaccine> getVaccines() {
    return listDtVaccines;
  }

  public DTVaccine getVaccineById(Long id) {
    try {
      return iVaccineController.getVaccineById(id);
    } catch (Exception e) {
      return new DTVaccine();
    }
  }


}
